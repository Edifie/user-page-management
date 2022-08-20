package teste.domain.dao;

import com.mysql.cj.log.Log;
import teste.domain.User;
import teste.domain.UserImpl;


import teste.servicos.UserService;

import java.util.List;

public class UserDao extends AbstractDao<User> {


    @Override
    public Class obtainDomainClass() {
        return UserImpl.class;
    }

    private UserDao() {

    }


    private static UserDao instance = new UserDao();

    protected static UserDao getInstance() {
        return instance;
    }


}
