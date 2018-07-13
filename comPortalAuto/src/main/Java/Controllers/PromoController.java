package Controllers;

import DBEntities.CarEntity;
import DBEntities.PromoEntity;
import DBEntities.UserasEntity;
import Managers.PromoManager;
import Managers.UserASManager;
import Security.CookieSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class PromoController {

    private static final String NORESULTS = "По введённым параметрам ничего не найдено!";
    private final static String NUMALREADYUSED = "Данный регистрационный номер уже занят!";
    private final static String PROMOWINDOW = "createPromo";
    private final static String USERWINDOW = "usermain";
    private final static String ADMINWINDOW = "adminmain";
    private final static String GUESTWINDOW = "main";

    @RequestMapping(value = "searchPromo", method = RequestMethod.GET)
    public String searchPromo(HttpServletRequest request) {
        System.out.println("****Search form****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        String costFrom = request.getParameter("costFrom");
        String costTo = request.getParameter("costTo");
        String brend = request.getParameter("mark");
        String model = request.getParameter("model");
        String power = request.getParameter("power");
        String date = request.getParameter("date");
        System.out.println("****Поля формы заполены. Идёт проверка данных ...****");
        PromoManager manager = new PromoManager();
        List<PromoEntity> promos = manager.getPromos(costFrom, costTo, brend, model, power, date);
        if (promos == null || promos.size() == 0) {
            System.out.println("****Ничего не найдено****");
            request.setAttribute("infoSearch", NORESULTS);
            if (res == CookieSecurity.GUEST)
                return GUESTWINDOW;
            else if (res == CookieSecurity.USER) {
                request.setAttribute("userName", security.getCookieParams()[1]);
                return USERWINDOW;
            } else {
                request.setAttribute("userName", security.getCookieParams()[1]);
                return ADMINWINDOW;
            }
        }
        StringBuffer str = new StringBuffer();
        int i = 1;
        System.out.println("****Идёт заполнение формы****");
        for (PromoEntity e : promos) {
            str.append(String.format("****ОБЪЯВЛЕНИЕ № %s****<br><pre>    Дата объявления: %s<br>    Стоимость авто: %sр" +
                            "<br>    Номер авто: %s <br>  Характеристики авто: <br>        Марка: %s<br>        Модель: %s<br>        Тип кузова: %s" +
                            "<br>        Мощность: %s<br>    Информация о владельце: <br>        ФИО: %s<br>        Телефон: 8%s" +
                            "<br>    Дополнительная информация: %s</pre>", i, e.getDate(), e.getPrice(), e.getCar().getNum(), e.getCar().getBrend(),
                    e.getCar().getModel(), e.getCar().getBody(), e.getCar().getPower(), e.getOwner().getInfo().getFio(),
                    e.getOwner().getInfo().getMphone(), e.getInfo()));
            i++;
        }
        request.setAttribute("infoSearch", str.toString());
        if (res == CookieSecurity.GUEST)
            return GUESTWINDOW;
        String[] mas = security.getCookieParams();
        request.setAttribute("userName", mas[1]);
        if (res == CookieSecurity.ADMIN)
            return ADMINWINDOW;
        else
            return USERWINDOW;
    }

    @RequestMapping(value = "createPromo", method = RequestMethod.GET)
    public String createPromo(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("****CreatePromo form****");
        CookieSecurity security = new CookieSecurity();
        int res = security.authorization(request);
        if (res == CookieSecurity.GUEST)
            return GUESTWINDOW;
        String[] mas = security.getCookieParams();
        UserASManager userMan = new UserASManager();
        UserasEntity user = userMan.getUserasByID(Integer.valueOf(mas[0]));
        if (user != null) ;
        else {
            System.out.println("****Ошибка доступа: не существующий пользователь (попытка создать объявление)****");
            return GUESTWINDOW;
        }
        String cost = request.getParameter("cost");
        String brend = request.getParameter("mark");
        String model = request.getParameter("model");
        String power = request.getParameter("power");
        String num = request.getParameter("num");
        Date date = (Date) request.getAttribute("date");
        String info = request.getParameter("info");
        Date curDate = new Date();
        System.out.println("****Поля формы заполены. Идёт проверка данных ...****");
        PromoManager manager = new PromoManager();
        Integer id = manager.addPromo(user, new CarEntity(num, user, brend, model, Integer.valueOf(power),
                "", date, 1), curDate, Integer.valueOf(cost), info, "active");
        if(id == null) {
            request.setAttribute("infoNum", NUMALREADYUSED);
            return PROMOWINDOW;
        }
        request.setAttribute("userName", mas[1]);
        if (res == CookieSecurity.ADMIN)
            return ADMINWINDOW;
        else
            return USERWINDOW;
    }
}
