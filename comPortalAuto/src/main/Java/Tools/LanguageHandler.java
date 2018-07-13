package Tools;

import java.io.UnsupportedEncodingException;

public class LanguageHandler {


    private final static char[] RUS = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
            'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ч', 'Ц', 'Ш', 'Щ', 'Э', 'Ю', 'Я', 'Ы',
            'Ъ', 'Ь'};
    private final static String[] LAT = {"A", "B", "V", "G", "D", "E", "Jo", "Zh", "Z", "I", "J", "K", "L", "M", "N",
            "O", "P", "R", "S", "T", "U", "F", "H", "CH", "TS", "SH", "CSH", "E", "JU", "JA",
            "Y", "`", "'"};

    public static String russianToLatin(String s) {
        if (s == null)
            return null;
        StringBuffer str = new StringBuffer();
        try {
            String input = new String(s.getBytes("ISO-8859-1"), "UTF-8");
            int len = input.length();
            input = input.toUpperCase();
            for (int i = 0; i < len; i++) {
                char c = input.charAt(i);
                if ((c >= 'А' && c <= 'Я') || c == 'Ё' || c == 'Й') {
                    int k = mapRus(c);
                    if (k == -1)
                        throw new LangTransformException("Невозможно преобразовать символ: " + c);
                    else {
                        String nc = LAT[k];
                        str.append(nc);
                    }
                } else
                    str.append(c);
            }
        } catch (LangTransformException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public static String latinToRussian(String s) {
        if (s == null)
            return null;
        StringBuffer str = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if ((c >= 'А' && c <= 'Я') || c == 'Ё' || c == 'Й') {
                int k = mapRus(c);
            } else
                str.append(c);
        }
        return str.toString();
    }

    private static int mapRus(char c) {
        int len = RUS.length;
        for (int i = 0; i < len; i++)
            if (c == RUS[i])
                return i;
        return -1;
    }

    private static int mapLat(String c) {
        int len = LAT.length;
        for (int i = 0; i < len; i++)
            if (c.equals(LAT[i]))
                return i;
        return -1;
    }
}
