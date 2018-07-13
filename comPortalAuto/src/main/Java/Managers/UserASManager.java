package Managers;

import DBEntities.AccountEntity;
import DBEntities.UserEntity;
import DBEntities.UserasEntity;
import Tools.LanguageHandler;
import Tools.MD5Encryption;
import org.hibernate.*;

import java.util.Iterator;
import java.util.List;

public class UserASManager {

    private final static int BLOCKED = -2;
    private final static int WRONGLOGPAS = -1;

    private static SessionFactory factory;

    public UserASManager() {
        init();
    }

    public void init() {
        this.factory = DBConfigurater.getInstance().getSessionFactory();
    }

    public UserEntity addUser(String fio, String addr, String mphone, String email) {
        Session session = factory.openSession();
        Transaction tx = null;
        UserEntity user = null;
        try {
            tx = session.beginTransaction();
            user = new UserEntity(fio, addr, mphone, email);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public AccountEntity addAccount(String login, String password, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        AccountEntity account = null;
        try {
            tx = session.beginTransaction();
            account = new AccountEntity(login, MD5Encryption.md5Cipher(password), status);
            session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return account;
    }

    public Integer addUserAS(AccountEntity account, UserEntity user, String role) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            user.setFio(LanguageHandler.russianToLatin(user.getFio().toUpperCase()));
            user.setAddr(LanguageHandler.russianToLatin(user.getAddr().toUpperCase()));
            account.setPass(MD5Encryption.md5Cipher(account.getPass()));
            UserasEntity userAS = new UserasEntity(account, user, role);
            employeeID = (Integer) session.save(userAS);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    public UserasEntity getUserasByID(Integer id) {
        Session session = factory.openSession();
        Transaction tx = null;
        UserasEntity user = null;
        try {
            tx = session.beginTransaction();
            user = session.get(UserasEntity.class, id);
            System.out.println(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public void updateUser(Integer id, String fio, String addr, String mphone, String email) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, id);
            if (fio != null)
                user.setFio(LanguageHandler.russianToLatin(fio.toUpperCase()));
            if (addr != null)
                user.setAddr(LanguageHandler.russianToLatin(addr.toUpperCase()));
            if (mphone != null)
                user.setMphone(mphone);
            if (email != null)
                user.setEmail(email);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateAccount(String login, String password, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AccountEntity account = session.get(AccountEntity.class, login);
            if (password != null && !password.equals(""))
                account.setPass(password);
            if (status != null)
                account.setStat(status);
            session.update(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Проверка существования аккаунта по логину
     * Возвращает строку с результатом: логин пользователя или уведомляет об ошибке
     */

    public int checkAccount(String login, String password2) {
        String password = MD5Encryption.md5Cipher(password2);
        int result = WRONGLOGPAS;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM AccountEntity e WHERE e.login = :log AND e.pass = :pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
            AccountEntity account = (AccountEntity) query.uniqueResult();
            if (account != null)
                if (account.getPass().equals(password)) {
                    if (!account.getStat().equals(Constants.BLOCKED)) {
                        query = session.createQuery("FROM UserasEntity e WHERE e.ac = :account");
                        query.setParameter("account", account);
                        UserasEntity user = (UserasEntity) query.uniqueResult();
                        account.setStat(Constants.ACTIVE);
                        int id = user.getId();
                        result = id;
                    } else
                        result = BLOCKED;
                }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public AccountEntity isLoginExist(String login) {
        Session session = factory.openSession();
        Transaction tx = null;
        AccountEntity ob = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM AccountEntity e WHERE e.login = :log");
            query.setParameter("log", login);
            ob = (AccountEntity) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ob;
    }

    public void changeAccountStatus(int id, String stat) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AccountEntity account = session.get(AccountEntity.class, id);
            account.setStat(stat);
            session.update(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listUserAS() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List usersAS = session.createQuery("FROM UserasEntity").list();
            for (Iterator iterator = usersAS.iterator(); iterator.hasNext(); ) {
                UserasEntity userAS = (UserasEntity) iterator.next();
                System.out.println(userAS);
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
