package Managers;

import java.util.HashMap;
import java.util.Map;

public class MapCode {

    private static Map <Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(-2, "Пользователь заблокирован по решению администратора!");
        map.put(-1, "Неверные логин или пароль!");
    }

    public static String getStringByCode(int id) {
        return map.get(id);
    }

}
