package teste.domain.dao;

public class DaoFactory
{


    //Component
    public static ComponentDao createComponentDao()
    {
        return ComponentDao.getInstance();
    }

    //Page
    public static PageDao createPageDao(){
        return PageDao.getInstance();
    }

    //Section
    public static SectionDao createSectionDao(){
        return SectionDao.getInstance();
    }

    //User
    public static UserDao createUserDao(){
        return UserDao.getInstance();
    }

    //UserSession
    public static UserSessionDao createUserSessionDao(){
        return UserSessionDao.getInstance();
    }


}
