package teste.domain.dao;

import teste.domain.UserSession;
import teste.domain.UserSessionImpl;

public class UserSessionDao extends AbstractDao<UserSession>{
    @Override
    public Class obtainDomainClass() {
        return UserSessionImpl.class;
    }

    private UserSessionDao(){

    }

    private static UserSessionDao instance = new UserSessionDao();

    protected static UserSessionDao getInstance(){
        return instance;
    }
}
