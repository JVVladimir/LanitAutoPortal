package Managers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConfigurater {

    private static final SessionFactory ourSessionFactory;

    private static DBConfigurater ourInstance = new DBConfigurater();

    public static DBConfigurater getInstance() {
        return ourInstance;
    }

    private DBConfigurater() {
    }

    static {
        try {
            Configuration conf = new Configuration();
            ourSessionFactory = conf.configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("****Связь с БД не установлена****");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }

    public void shutdown() {
        ourSessionFactory.close();
    }
}
