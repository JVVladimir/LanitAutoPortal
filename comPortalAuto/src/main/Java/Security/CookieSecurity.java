package Security;

import DBEntities.UserasEntity;
import Managers.Constants;
import Managers.UserASManager;
import Tools.CookieName;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSecurity {

    public static final int ADMIN = 1;
    public static final int USER = 0;
    public static final int GUEST = -1;
    private static final int LEN = 3;

    // id, login, role
    private String[] cookieParams;

    public CookieSecurity() {
        cookieParams = new String[LEN];
    }

    public int authorization(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            System.out.println("****Ошибка создания объявления: Куки файлы не найдены*****");
            return GUEST;
        }
        String name;
        for (int i = 0; i < cookies.length; i++) {
            name = cookies[i].getName();
            if (name.equals(CookieName.ROLE.name()))
                cookieParams[2] = cookies[i].getValue();
            else if (name.equals(CookieName.ID.name()))
                cookieParams[0] = cookies[i].getValue();
            else if (name.equals(CookieName.LOGIN.name()))
                cookieParams[1] = cookies[i].getValue();
        }
        String id = cookieParams[0];
        String login = cookieParams[1];
        if (id == null || id.equals("") || login == null || login.equals(""))
            return GUEST;
        Integer i;
        try {
            i = Integer.valueOf(id);
        } catch (Exception ex) {
            return GUEST;
        }
        UserASManager manager = new UserASManager();
        UserasEntity user = manager.getUserasByID(i);
        if (user == null || !user.getAc().getLogin().equals(login) ||
                user.getAc().getStat().equals(Constants.BLOCKED) || user.getAc().getStat().equals(Constants.OFFLINE))
            return GUEST;
        System.out.println(String.format("Данные куки: [id: %s; login: %s; role: %s]", cookieParams[0], cookieParams[1], cookieParams[2]));
        if (cookieParams[2].equals(Constants.ROLEA))
            return ADMIN;
        else if (cookieParams[2].equals(Constants.ROLEU))
            return USER;
        else
            return GUEST;
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return;
        UserASManager manager = new UserASManager();
        UserasEntity user = null;
        String name;
        for (int i = 0; i < cookies.length; i++) {
            name = cookies[i].getName();
            if (name.equals(CookieName.ROLE.name())) {
                cookies[i].setValue(null);
                response.addCookie(cookies[i]);
            } else if (name.equals(CookieName.ID.name())) {
                user = manager.getUserasByID(Integer.valueOf(cookies[i].getValue()));
                cookies[i].setValue(null);
                response.addCookie(cookies[i]);
            } else if (name.equals(CookieName.LOGIN.name())) {
                cookies[i].setValue(null);
                response.addCookie(cookies[i]);
            }
        }
        if(user.getAc().getStat().equals(Constants.BLOCKED));
        else
            manager.changeAccountStatus(user.getAc().getId(), Constants.OFFLINE);
    }

    public String[] getCookieParams() {
        return cookieParams;
    }
}
