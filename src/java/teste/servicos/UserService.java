package teste.servicos;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import teste.domain.User;
import teste.domain.UserImpl;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.logic.HasRole;
import teste.servicepack.security.logic.IsAuthenticated;
import teste.servicepack.security.logic.Transaction;
import teste.utils.HibernateUtils;

import java.util.List;

public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);


    @Transaction
    @IsAuthenticated
    @HasRole(role = "admin")


    public JSONObject addUser (JSONObject user){

        UserImpl userObj = UserImpl.fromJson(user);

        if (userObj.getId() > 0){
            UserImpl objP = (UserImpl) DaoFactory.createUserDao().get(userObj.getId());
            objP.setName(userObj.getName());
            objP.setRoles(userObj.getRoles());
            objP.setEmail(userObj.getEmail());
            objP.setPassword(userObj.getPassword());
            objP.setUsername(userObj.getUsername());

            JSONObject jsonObject = new JSONObject(objP.toJson());
            return jsonObject;
        }else {
            HibernateUtils.getCurrentSession().save(userObj);
        }
        return new JSONObject(userObj.toJson());
    }


    @Transaction
    @IsAuthenticated
    @HasRole (role = "admin")
    public JSONObject loadUser (JSONObject idObj){

        Long id = idObj.getLong("id");
        UserImpl userP = (UserImpl) DaoFactory.createUserDao().get(id);
        JSONObject jsonObject = new JSONObject(userP.toJson());

        return jsonObject;
    }


    @Transaction
    @IsAuthenticated
    public JSONArray LoadAll(JSONObject alo) {

        logger.info("User Service");

        List<User> users = DaoFactory.createUserDao().createCriteria().list();

        JSONArray results = new JSONArray();

        for (User user : users) {
            results.put(new JSONObject(((UserImpl) user).toJson()));
        }
        return results;
    }



    @Transaction
    @IsAuthenticated
    @HasRole(role = "admin")
    public void deleteUser(JSONObject user){

        User user1 = (User) HibernateUtils.getCurrentSession().load(User.class, user.getLong("id"));
        logger.info("DELETE USER: " + user1.getId());
        HibernateUtils.getCurrentSession().delete(user1);

    }
}
