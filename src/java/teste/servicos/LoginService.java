package teste.servicos;

import teste.domain.User;
import teste.domain.UserSession;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.SecurityContextProvider;
import teste.servicepack.security.logic.Transaction;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class LoginService {



    @Transaction
    public User checkLogin (String username, String password) throws Throwable{

        System.out.println("##########cookie = " + null);
        String cookie = SecurityContextProvider.getInstance().getSecuritySessionContext().getRequester();
        System.out.println(cookie);
        System.out.println("##########cookie = " + cookie);
        UserSession session = DaoFactory.createUserSessionDao().get(cookie);
        List<User> users = ( List<User>) DaoFactory.createUserDao().createCriteria()

                    .add(eq("username",username))
                    .add(eq("password",password))
                    .list();

        if(users.size() == 0)
            return null;

        session.setUser(users.get(0));
        return users.get(0);
    }


}
