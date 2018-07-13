package Managers;

import DBEntities.*;
import Tools.LanguageHandler;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PromoManager {

    private static SessionFactory factory;

    public PromoManager() {
        init();
    }

    public void init() {
        this.factory = DBConfigurater.getInstance().getSessionFactory();
    }

    public CarEntity addCar(String num, UserasEntity userAS, String brend,
                            String model, int power, String body, Date date, int mileage) {
        Session session = factory.openSession();
        Transaction tx = null;
        CarEntity car = null;
        try {
            tx = session.beginTransaction();
            car = new CarEntity(num, userAS, brend, model, power, body, date, mileage);
            session.save(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public Integer addPromo(UserasEntity userAS, CarEntity car, Date date, int price, String info, String stat) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer promoID = null;
        try {
            tx = session.beginTransaction();
            car.setBrend(car.getBrend().toUpperCase());
            car.setModel(car.getModel().toUpperCase());
            PromoEntity promo = new PromoEntity(userAS, date, car, price, LanguageHandler.russianToLatin(info), stat);
            promoID = (Integer) session.save(promo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return promoID;
    }

    public void updateCar(Integer id, String num, String brend,
                          String model, int power, String body, Date date, int mileage) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CarEntity car = session.get(CarEntity.class, id);
            if (num != null)
                car.setNum(num);
            if (brend != null)
                car.setBrend(brend);
            if (model != null)
                car.setModel(model);
            if (power > 0)
                car.setPower(power);
            if (body != null)
                car.setBody(body);
            if (date != null)
                car.setDate(date);
            if (mileage > 0)
                car.setMileage(mileage);
            session.update(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Удаление объявления по номеру машины
     */

    public int deletePromo(String num) {
        int res = -1;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM PromoEntity p WHERE p.car.num = :n");
            query.setParameter("n", num);
            PromoEntity promo = (PromoEntity) query.uniqueResult();
            if(promo != null) {
                session.delete(promo);
                res = 0;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

    public int deletePromo(String num, int userasId) {
        int res = -1;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM PromoEntity p WHERE p.car.num = :num AND p.owner.id = :id");
            query.setParameter("num", num);
            query.setParameter("id",userasId);
            PromoEntity promo = (PromoEntity) query.uniqueResult();
            if (promo != null) {
                session.delete(promo);
                res = 0;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

    public void updatePromo(Integer id, Date date, CarEntity car, int price, String info, String stat) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PromoEntity promo = session.get(PromoEntity.class, id);
            if (date != null)
                promo.setDate(date);
            if (car != null)
                promo.setCar(car);
            if (price > 0)
                promo.setPrice(price);
            if (info != null)
                promo.setInfo(info);
            if (stat != null)
                promo.setStat(stat);
            session.update(promo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Поиск объявлений по параметрам (машина, цена, дата)
    public List<PromoEntity> getPromos(String priceFrom, String priceTo, String brend2, String model2, String power, String date) {
        String brend = brend2.toUpperCase();
        String model = model2.toUpperCase();
        StringBuffer defaultQuery = new StringBuffer("FROM PromoEntity p WHERE p.car = car.id");
        Session session = factory.openSession();
        Transaction tx = null;
        List promos = null;
        try {
            tx = session.beginTransaction();
            if (priceFrom != null && !priceFrom.equals(""))
                defaultQuery.append(" and p.price >= " + priceFrom.trim().split(" ")[0]);
            if (priceTo != null && !priceTo.equals(""))
                defaultQuery.append(" and p.price <= " + priceTo.trim().split(" ")[0]);
            if (brend != null && !brend.equals(""))
                defaultQuery.append(String.format(" and car.brend = '%s'", brend.trim().split(" ")[0]));
            if (model != null && !model.equals(""))
                defaultQuery.append(String.format(" and car.model = '%s'", model.trim().split(" ")[0]));
            if (power != null && !power.equals(""))
                defaultQuery.append(" and car.power = " + power.trim().split(" ")[0]);
            if (date != null && !date.equals(""))
                defaultQuery.append(String.format(" and p.date >= '%s'", date.trim().split(" ")[0]));
            System.out.println(defaultQuery);
            Query query = session.createQuery(defaultQuery.toString());
            System.out.println(query);
            promos = query.list();
            System.out.println(promos);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return promos;
    }

    // Получить список всех доступных объявлений
    public List<PromoEntity> getAllPromos() {
        Session session = factory.openSession();
        Transaction tx = null;
        List promos = null;
        try {
            tx = session.beginTransaction();
            promos = session.createQuery("FROM PromoEntity").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return promos;
    }

    public void listPromo() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List promos = session.createQuery("FROM PromoEntity").list();
            for (Iterator iterator = promos.iterator(); iterator.hasNext(); ) {
                PromoEntity promo = (PromoEntity) iterator.next();
                System.out.println(promo);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
