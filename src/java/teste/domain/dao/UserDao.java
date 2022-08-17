package teste.domain.dao;

import teste.domain.User;
import teste.domain.UserImpl;

public class UserDao extends AbstractDao<User>{
    @Override
    public Class obtainDomainClass() {
        return UserImpl.class;
    }

    private UserDao(){

    }

    private static UserDao instance = new UserDao();

    protected static UserDao getInstance(){
        return instance;
    }

}
