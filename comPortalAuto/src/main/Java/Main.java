import DBEntities.*;
import Managers.PromoManager;
import Managers.UserASManager;
import Tools.LanguageHandler;
import Tools.MD5Encryption;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.jws.soap.SOAPBinding;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;


public class Main {

    // Объект создающий новые сессии
   /* private static final SessionFactory ourSessionFactory;

    // Начальная инициализация компонентов
    static {
        try {
            // Подгрузка конфигурационного файла xml
            Configuration conf = new Configuration();
            // Session factory создаётся один раз на всё приложение
            ourSessionFactory = conf.configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    /**
     * Получение новой сессии из фабрики
     * В программе может работать одновременно много сессий!!!!!
     */

  /*  public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    // Закрытие фабрики сессий
    public static void shutdown() {
        ourSessionFactory.close();
    }*/


    public static void main(String[] args) throws Exception {
        /*UserASManager m = new UserASManager();
        PromoManager manager = new PromoManager();
        UserasEntity user = m.getUserasByID(6);
        CarEntity car = manager.addCar("12345234", user,"BMW","3100i",125,"Седан",null,25000);
        manager.addPromo(user, car, new Date(10,10,10),15000,null,"active");*/
      //  PromoManager manager = new PromoManager();
      //  List<PromoEntity> list = manager.getPromos("1000", null, "BMW", null, "126", null);
        //UserASManager manager = new UserASManager();
        //System.out.println(manager.createPromo("Admin","123"));
        //System.out.println(MD5Encryption.md5Cipher("Vovaerughrugheruhnuiqerhniqerhherqihherihreierh"));
        // System.out.println(LanguageHandler.russianToLatin("Владимир Александрович Кашицын"));
        /*Charset c = Charset.forName("UTF-8");
        // c.encode("Владимир").;
        String t = new String("Владимир".getBytes(),"ISO-8859-1");
        System.out.println(t);

        String s = new String(t.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(s);*/
        /** *
         * "From UserEntity" - перечисление всех сущностей
         * "SELECT e.fio FROM UserEntity e WHERE e.id = 3" - вывод поля FIO с условием
         * "FROM UserEntity e WHERE e.id = 3" - вывод всех сущностей, подходящий под условие
         * "FROM UserEntity e WHERE e.addr = :city" - аналог Prepared Statement - query.setParameter("city","Germany");
         * "SELECT MAX(e.id) FROM UserEntity e" - поиск максимального идентификаторв
         * Год отсчитывается в SQL 0 - 1900, месяц 0 - 01
         * query.uniqueResult() - возвращает один экземпляр или null
         */
       /* Query query = s.createQuery("FROM CAR c WHERE e.owner = 2");
        for (Object o: query.list())
            System.out.println(" " + o);*/
        // Date d = new Date(0,2,16);
       /* UserASManager manager = new UserASManager();
        manager.init();
        System.out.println(manager.createPromo("Admin", "admin"));*/
        //manager.init(ourSessionFactory);
      //  AccountEntity ac = new AccountEntity("Admin3", "admin", "active");
      //  manager.addAccount("Admin3", "admin", "active");
        //UserEntity user = new UserEntity("Bruce Lee5", "Russia", "89163412237", "3446655");
        //System.out.println(manager.addUserAS(ac, user, "A"));
        //manager.listUserAS();
        //UserasEntity us = new UserasEntity(ac, user, "A");
        //Transaction t = s.beginTransaction();
        // s.save(ac);
        // s.save(user);
        //  t.commit();
        //   t = s.beginTransaction();
        // s.save(us);
        // t.commit();
        //s.close();
       // shutdown();
       // System.out.println("Sessions closed!");
       // System.exit(0);
    }
}