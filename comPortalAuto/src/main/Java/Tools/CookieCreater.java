package Tools;

import javax.servlet.http.Cookie;

public class CookieCreater {

    public static Cookie createCookie(CookieName cName, String value, int timeLife, boolean isSecure) {
        String name = getStringByCookieName(cName);
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(timeLife);
        cookie.setSecure(isSecure);
        return cookie;
    }

    public static Cookie createCookie(CookieName cName, String value, int timeLife) {
        String name = getStringByCookieName(cName);
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(timeLife);
        cookie.setSecure(false);
        return cookie;
    }

    private static String getStringByCookieName(CookieName cName) {
        String name;
        switch (cName) {
            case ID: name = "ID"; break;
            case LOGIN: name = "LOGIN"; break;
            case ROLE: name = "ROLE"; break;
            default: name = "";
        }
        return name;
    }
}
