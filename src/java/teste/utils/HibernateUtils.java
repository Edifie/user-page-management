package teste.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;


import java.sql.SQLException;

public class HibernateUtils implements Runnable{

    //    private static Session session;
    private static final Logger logger = Logger.getLogger(HibernateUtils.class);
    private static SessionFactory sessionFactory;


    static
    {
        try
        {
           createFactory();
        }
        catch (Throwable e)
        {
            logger.fatal(e,e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static void createFactory()
    {
        Configuration config = new Configuration();
        config.configure(Thread.currentThread().getContextClassLoader().getResource("teste/domain/hibernate.cfg.xml"));
        sessionFactory = config.buildSessionFactory();

    }



    public static SessionFactory getSessionFactory()
    {
        if(sessionFactory.isClosed())
           createFactory();
        return sessionFactory;
    }



    public static synchronized Session openSession()
    {
//        if(!session.isOpen())
   //      return sessionFactory.openSession();
//        return session;
        return getCurrentSession();
    }
    public static synchronized Session getCurrentSession() throws IllegalStateException
    {
        return getSessionFactory().getCurrentSession();
    }


    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }


    public static void testeSession(){
        Session sess = HibernateUtils.getCurrentSession();
        System.out.println(sess);

    }

    public void run()
    {
        Session sess = getCurrentSession();
        Transaction t2 = sess.beginTransaction();

        t2.begin();

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        t2.commit();
    }
}

