package Controllers;

import DBEntities.AccountEntity;
import DBEntities.UserEntity;
import Managers.Constants;
import Managers.MapCode;
import Managers.UserASManager;
import Tools.CookieCreater;
import Tools.CookieName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Данный класс-контроллер обрабатывает запросы на регистрацию пользователя, а также входа пользователя в систему
 */


@Controller
public class RegController {

    private final static String REPPASS = "Пароли не совпадает";
    private final static String ALREADYEXISTLOGIN = "Логин уже существует";
    private final static String ADMIN = "Admin";

    private final static String USERWINDOW = "usermain";
    private final static String ADMINWINDOW = "adminmain";
    private final static String GUESTWINDOW = "main";
    private final static String REGWINDOW = "registr";

    private final static int TIMELIFE = 3600;

    /**
     * Метод выполняет вход пользователя в систему (проверяет логин, пароль)
     */

    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    public String checkAccount(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("****CheckAccount form****");
        ArrayList<String> list = getPostMessage(request);
        String login = list.get(0);
        String password = list.get(1);
        System.out.println("****Поля формы заполены. Идёт проверка данных ...****");
        UserASManager manager = new UserASManager();
        int result = manager.checkAccount(login, password);
        if (result > -1) {
            System.out.println(String.format("****Удачная аутентификация пользователя: %s****", login));
            request.setAttribute("username", login);
            Cookie cookieID = CookieCreater.createCookie(CookieName.ID, String.valueOf(result), TIMELIFE);
            Cookie cookieLogin = CookieCreater.createCookie(CookieName.LOGIN, login, TIMELIFE);
            response.addCookie(cookieID);
            response.addCookie(cookieLogin);
            Cookie cookieRole;
            if (login.equals(ADMIN)) {
                cookieRole = CookieCreater.createCookie(CookieName.ROLE, Constants.ROLEA, TIMELIFE);
                response.addCookie(cookieRole);
                request.setAttribute("userName", login);
                return ADMINWINDOW;
            } else {
                cookieRole = CookieCreater.createCookie(CookieName.ROLE, Constants.ROLEU, TIMELIFE);
                response.addCookie(cookieRole);
                request.setAttribute("userName", login);
                return USERWINDOW;
            }
        } else {
            System.out.println("****Вход в портал не выполнен: причины разные****");
            request.setAttribute("info", MapCode.getStringByCode(result));
            return GUESTWINDOW;
        }
    }

    /**
     * Метод пересылает на окно регистрации
     */

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String sendRegForm() {
        System.out.println("****Send registration form****");
        return REGWINDOW;
    }

    /**
     * Метод производит регистрацию нового пользователя и добавляет его в БД
     */

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String registration(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("****Registration form****");
        ArrayList<String> list = getPostMessage(request);
        String login = list.get(0);
        String password = list.get(1);
        String repPassword = list.get(2);
        String fio = list.get(3);
        String address = list.get(4);
        String phone = list.get(5);
        String email = list.get(6);
        if (email.equals(""))
            email = null;
        if (!password.equals(repPassword)) {
            request.setAttribute("infoPasRep", REPPASS);
            return REGWINDOW;
        }
        UserASManager manager = new UserASManager();
        if (manager.isLoginExist(login) != null) {
            System.out.println("****Login already exists****");
            request.setAttribute("infoLogin", ALREADYEXISTLOGIN);
            return REGWINDOW;
        }
        Integer id = manager.addUserAS(new AccountEntity(login, password, Constants.ACTIVE),
                new UserEntity(fio, address, phone, email), Constants.ROLEU);
        if (id == null || id < 0)
            return REGWINDOW;
        request.setAttribute("info", login);
        Cookie cookieID = CookieCreater.createCookie(CookieName.ID, String.valueOf(id), TIMELIFE);
        Cookie cookieLogin = CookieCreater.createCookie(CookieName.LOGIN, login, TIMELIFE);
        response.addCookie(cookieID);
        response.addCookie(cookieLogin);
        Cookie cookie = CookieCreater.createCookie(CookieName.ROLE, Constants.ROLEU, TIMELIFE);
        response.addCookie(cookie);
        System.out.println("****New userAS are created****");
        return GUESTWINDOW;
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
