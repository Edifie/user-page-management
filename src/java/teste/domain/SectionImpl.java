package teste.domain;

import com.owlike.genson.Genson;
import org.json.JSONObject;

public class SectionImpl extends Section{

     /*
    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", page=" + page +
                ", components=" + components +
                '}';
    }

     */


    static Genson genson = new Genson.Builder().useMethods(true)
            .useFields(false)
            .exclude(Object.class)
            .useClassMetadata(true)
            .useRuntimeType(true)
            .include("id", Section.class)
            .include("tittle", Section.class)
            .include("idPage", Section.class)
            .create();

    public static SectionImpl fromJson (JSONObject jsonObject){
        return genson.deserialize(jsonObject.toString(), SectionImpl.class);
    }

    public String toJson(){
        return genson.serialize(this);
    }


    @Override
    public String toString() {
        return "Section{" +
                "id=" + getId() +
                ", tittle='" + getTittle() + '\'' +
                ", page=" + getPage().getId() +
                '}';
    }




}
