package Test;

import DBEntities.AccountEntity;
import DBEntities.CarEntity;
import DBEntities.UserEntity;
import DBEntities.UserasEntity;
import Managers.PromoManager;
import Managers.UserASManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class Test {

    // Объект создающий новые сессии
    private static final SessionFactory ourSessionFactory;

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
    }

    /**
     * Получение новой сессии из фабрики
     * В программе может работать одновременно много сессий!!!!!
     */

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    // Закрытие фабрики сессий
    public static void shutdown() {
        ourSessionFactory.close();
    }

    public static void main(String[] args) {
        Test.startTest();
    }

    public static void startTest() {
        UserASManager manager = new UserASManager();
        PromoManager promoManager = new PromoManager();
       // promoManager.init(ourSessionFactory);
      //  manager.init(ourSessionFactory);
        /*Date d = new Date(61, 1, 24);
        AccountEntity ac = new AccountEntity("Admin4", "admin", "active");
        UserEntity user = new UserEntity("Bruce Lee6", "Russia", "89163412237", "3446655");
        int id = manager.addUserAS(ac, user, "U");
        UserasEntity us = manager.getUserasByID(id);
        CarEntity car = new CarEntity("12345667", us, "BMW", "3000", 120, "Sedan", null, 40000);
        CarEntity car2 = new CarEntity("22245667", us, "AUDI", "2300", 320, "Crossover", null, 70000);

        promoManager.addPromo(us, car, d, 45000, "nothing", "actual");

        promoManager.addPromo(us, car2, d, 75000, "buy rapidly", "actual");*/

        /*List list = promoManager.getPromos(45000);
        System.out.println(list);
        //shutdown();
        System.out.println("Sessions closed!");
        System.exit(0);*/
    }
    /**
     * Нельзя создать несколько объявлений для одной машины!
     * Можно создать несколько объявлений у одного пользователя
     * При создании нового Account сразу добавляется инфа о User и формируется объект UserAS. (Они действуют втроём)
     *
     * */
}
