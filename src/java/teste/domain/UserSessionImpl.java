package teste.domain;

import com.owlike.genson.Genson;
import org.json.JSONObject;

public class UserSessionImpl extends UserSession{

     /*
    @Override
    public String toString() {
        return "UserSession{" +
                "cookie='" + cookie + '\'' +
                ", user=" + user +
                '}';
    }

     */

    static Genson genson = new Genson.Builder().useMethods(true)
            .useFields(false)
            .exclude(Object.class)
            .useClassMetadata(true)
            .useRuntimeType(true)
            .include("cookie", UserSession.class)
            .include("user", UserSession.class)
            .include("user", User.class)
            .create();


    public static UserSessionImpl fromJson (JSONObject jsonObject){
        return genson.deserialize(jsonObject.toString(), UserSessionImpl.class);
    }

    public String toJson(){
        return genson.serialize(this);
    }


    @Override
    public String toString() {
        return "UserSession{" +
                "cookie='" + getCookie() + '\'' +
                ", user=" + getUser() +
                '}';
    }


}
