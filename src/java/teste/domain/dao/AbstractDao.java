package teste.domain.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import teste.utils.HibernateUtils;

import java.io.Serializable;

public abstract class AbstractDao<CLAZZ>
{

    public static Session getCurrentSession()
    {
        return HibernateUtils.getCurrentSession();
    }
    public static Transaction beginTransaction(){
        return getCurrentSession().beginTransaction();
    }
    public static void commitTransaction(){
        getCurrentSession().getTransaction().commit();
    }
    public static void rollbackTransaction()
    {
        getCurrentSession().getTransaction().rollback();
    }





    public abstract Class obtainDomainClass();




    public Criteria createCriteria(){
        return getCurrentSession().createCriteria(obtainDomainClass());
    }


    public CLAZZ load(Serializable id)
    {
        return (CLAZZ)  getCurrentSession().load(obtainDomainClass(),id);
    }

    public void save(CLAZZ c)
    {
        getCurrentSession().save(c);
    }

    public CLAZZ get(Serializable id){
        return(CLAZZ) getCurrentSession().get(obtainDomainClass(),id);
    }

    public void delete(CLAZZ c){
        getCurrentSession().delete(c);}

    public void refresh(CLAZZ c)
    {
        getCurrentSession().refresh(c);
    }

    public void update(CLAZZ c)
    {
        getCurrentSession().update(c);
    }



}
