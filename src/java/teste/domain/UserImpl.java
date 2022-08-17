package teste.domain;

import com.owlike.genson.Genson;
import org.json.JSONObject;

public class UserImpl extends User {

     /*
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

     */

    static Genson genson = new Genson.Builder()
            .useMethods(true)
            .useFields(false)
            .exclude(Object.class)
            .useClassMetadata(true)
            .useRuntimeType(true)
            .include("id", User.class)
            .include("name", User.class)
            .include("username", User.class)
            .include("password", User.class)
            .include("email", User.class)
            .include("roles", User.class)
            .create();

    public static UserImpl fromJson (JSONObject jsonObject){
        return genson.deserialize(jsonObject.toString(), UserImpl.class);
    }

    public String toJson(){
        return genson.serialize(this);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", roles='" + getRoles() + '\'' +
                '}';
    }


}
