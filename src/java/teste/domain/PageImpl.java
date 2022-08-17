package teste.domain;

import com.owlike.genson.Genson;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class PageImpl extends Page{

    /*
    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", roles='" + roles + '\'' +
                ", user=" + user +
                ", sections=" + sections +
                '}';
    }

     */

    static Genson genson = new Genson.Builder()
            .useMethods(true)
            .useFields(false)
            .exclude(Object.class)
            .useClassMetadata(true)
            .useRuntimeType(true)
            .include("id", Page.class)
            .include("tittle", Page.class)
            .include("roles", Page.class)
            .include("id", User.class)
            .include("id" , Section.class)
            .include("tittle", Section.class)
            .include("Components", Section.class)
            .include("id", Component.class)
            .include("text", Component.class)
            .include("img", Component.class)
            .create();


    public static PageImpl fromJson(JSONObject jsonObject){
        return genson.deserialize(jsonObject.toString(), PageImpl.class);
    }

    public String toJson(){
        return genson.serialize(this);
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + getId() +
                ", tittle='" + getTittle() + '\'' +
                '}';
    }
}
