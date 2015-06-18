package kr.pe.kwonnam.hibernatesutomtime;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HibernateBugReproduce {

    public static void main(String[] args) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        final Query query = session.createQuery("from User user where employee = ?1 and male = ?1 and old = ?2");
        query.setParameter("1", Boolean.TRUE);
        query.setParameter("2", Boolean.FALSE);

        final List list = query.list();
        System.out.println("Result : " + list);

        session.close();
        HibernateUtils.getSessionFactory().close();
    }
}
