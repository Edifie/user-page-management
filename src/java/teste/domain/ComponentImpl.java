package teste.domain;


import com.owlike.genson.Genson;
import org.json.JSONObject;

public class ComponentImpl extends Component{

     /*
    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", section=" + section +
                '}';
    }
     */

    static Genson genson = new Genson.Builder().useMethods(true)
            .useFields(false)
            .exclude(Object.class)
            .useClassMetadata(true)
            .useRuntimeType(true)
            .include("id", Component.class)
            .include("text", Component.class)
            .include("img", Component.class)
            .include("idSection", Component.class)
            .create();

    public static ComponentImpl fromJson (JSONObject jsonObject){
        return genson.deserialize(jsonObject.toString(),ComponentImpl.class);
    }

    public String toJson(){
        return genson.serialize(this);
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + getId() +
                ", text='" + getText() + '\'' +
                ", img='" + getId() + '\'' +
                ", section=" + getSection().getId() +
                '}';
    }

    

}
