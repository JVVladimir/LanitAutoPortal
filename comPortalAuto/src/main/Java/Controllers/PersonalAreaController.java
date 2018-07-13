package Controllers;

import DBEntities.AccountEntity;
import DBEntities.UserEntity;
import DBEntities.UserasEntity;
import Managers.Constants;
import Managers.PromoManager;
import Managers.UserASManager;
import Security.CookieSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class PersonalAreaController {

    private final static String ADMINWINDOW = "adminAccount";
    private final static String USERWINDOW = "userAccount";
    private final static String GUESTWINDOW = "main";
    private final static String LOGININVALID = "Пользователя не существует!";
    private final static String USERBLOCKED = "Пользователь заблокирован!";
    private final static String USERUNBLOCKED = "Пользователь разблокирован!";
    private final static String PROMOCANNOTDELETE = "Объявление невозможно удалить!";
    private final static String PROMODELETED = "Объявление удалено!";

    /**
     * Обрабатывает нажатие на кнопку "Личный кабинет"
     */

    @RequestMapping(value = "personalArea", method = RequestMethod.GET)
    public String redirectAccount(HttpServletRequest request) {
        System.out.println("****Personal area form****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST) {
            System.out.println("****Ошибка обновления данных пользователя: Куки файлы не найдены*****");
            return GUESTWINDOW;
        }
        UserASManager manager = new UserASManager();
        UserasEntity useras = manager.getUserasByID(Integer.valueOf(security.getCookieParams()[0]));
        UserEntity user = useras.getInfo();
        autoFillUserInfo(request, user);
        if (res == CookieSecurity.ADMIN)
            return ADMINWINDOW;
        else
            return USERWINDOW;
    }

    /**
     * Обрабатывает нажатие на кнопку "Обновить"
     */

    @RequestMapping(value = "updateUserData", method = RequestMethod.GET)
    public String updateUserData(HttpServletRequest request) {
        System.out.println("****Update user data form****");
        String fio = request.getParameter("fio");
        String addr = request.getParameter("addr");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST) {
            System.out.println("****Ошибка обновления данных пользователя: Куки файлы не найдены*****");
            return GUESTWINDOW;
        }
        String[] mas = security.getCookieParams();
        int id = Integer.valueOf(mas[0]);
        UserASManager manager = new UserASManager();
        UserasEntity useras = manager.getUserasByID(id);
        UserEntity user = useras.getInfo();
        manager.updateUser(user.getId(), fio, addr, phone, email);
        autoFillUserInfo(request, fio, addr, phone, email);
        if (res == CookieSecurity.ADMIN)
            return ADMINWINDOW;
        else
            return USERWINDOW;
    }

    /**
     * Перенаправляет на страницу создания нового объявления
     */

    @RequestMapping(value = "showPromoCreation", method = RequestMethod.GET)
    public String showPromoCreationForm(HttpServletRequest request) {
        System.out.println("****Show Promo creation form****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST) {
            System.out.println("****Ошибка создания нового объявления: Куки файлы не найдены*****");
            return GUESTWINDOW;
        }
        return "createPromo";
    }

    @RequestMapping(value = "logOut", method = RequestMethod.GET)
    public String logOutUser(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("****Log out user or admin****");
        CookieSecurity security = new CookieSecurity();
        security.logOut(request, response);
        return GUESTWINDOW;
    }


    @RequestMapping(value = "blockUser", method = RequestMethod.POST)
    public String editAccountAdmin(HttpServletRequest request) {
        System.out.println("***Block user****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST || res == CookieSecurity.USER || !security.getCookieParams()[1].equals("Admin")) {
            System.out.println("****Ошибка блокирования пользователя: нет прав*****");
            return GUESTWINDOW;
        }
        ArrayList<String> list = getPostMessage(request);
        String login = list.get(0);
        UserASManager manager = new UserASManager();
        AccountEntity account = manager.isLoginExist(login);
        if (account == null)
            request.setAttribute("info", LOGININVALID);
        if (account.getStat().equals(Constants.ACTIVE) || account.getStat().equals(Constants.OFFLINE)) {
            request.setAttribute("info", USERBLOCKED);
            manager.changeAccountStatus(account.getId(), Constants.BLOCKED);
        }
        else {
            request.setAttribute("info", USERUNBLOCKED);
            manager.changeAccountStatus(account.getId(), Constants.OFFLINE);
        }
        //autoFillUserInfo(request, user);
        return ADMINWINDOW;
    }

    @RequestMapping(value = "deletePromo", method = RequestMethod.POST)
    public String editAccountUser(HttpServletRequest request) {
        System.out.println("****Delete promo****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST) {
            System.out.println("****Ошибка блокирования пользователя: нет прав*****");
            return GUESTWINDOW;
        }
        ArrayList<String> list = getPostMessage(request);
        String num = list.get(0);
        PromoManager manager = new PromoManager();
        //autoFillUserInfo(request, user);
        if (security.getCookieParams()[1].equals("Admin")) {
            res = manager.deletePromo(num);
            if(res == 0)
                request.setAttribute("info2",PROMODELETED);
            else
                request.setAttribute("info2",PROMOCANNOTDELETE);
            return ADMINWINDOW;
        } else {
            res = manager.deletePromo(num, Integer.valueOf(security.getCookieParams()[0]));
            if(res == 0)
                request.setAttribute("info",PROMODELETED);
            else
                request.setAttribute("info",PROMOCANNOTDELETE);
            return USERWINDOW;
        }
    }

    private void autoFillUserInfo(HttpServletRequest request, UserEntity user) {
        request.setAttribute("fioSub", user.getFio());
        request.setAttribute("addrSub", user.getAddr());
        request.setAttribute("phoneSub", user.getMphone());
        request.setAttribute("emailSub", user.getEmail());
    }

    private void autoFillUserInfo(HttpServletRequest request, String fio, String addr, String phone, String email) {
        request.setAttribute("fioSub", fio);
        request.setAttribute("addrSub", addr);
        request.setAttribute("phoneSub", phone);
        request.setAttribute("emailSub", email);
    }

    private ArrayList<String> getPostMessage(HttpServletRequest request) {
        ArrayList<String> list = null;
        try {
            list = new ArrayList<>();
            BufferedReader reader = request.getReader();
            String s;
            while ((s = reader.readLine()) != null)
                list.add(s.substring(s.indexOf('=') + 1, s.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
