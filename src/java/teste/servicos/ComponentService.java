package teste.servicos;


import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import teste.domain.Component;
import teste.domain.ComponentImpl;
import teste.domain.Page;
import teste.domain.Section;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.logic.HasRole;
import teste.servicepack.security.logic.IsAuthenticated;
import teste.servicepack.security.logic.Transaction;
import teste.utils.HibernateUtils;

public class ComponentService {

    private static final Logger logger = Logger.getLogger(String.valueOf(ComponentService.class));


    //addComponent
    //@IsAuthenticated
    //@HasRole(role = "Admin")
    @Transaction
    public JSONObject addComponent(JSONObject Component){
        logger.info("Component");

        long idSection = Component.getLong("idSection");
        ComponentImpl obj = ComponentImpl.fromJson(Component);
        Section section = DaoFactory.createSectionDao().load(idSection);

        if (obj.getId() > 0){
            ComponentImpl objP = (ComponentImpl) DaoFactory.createComponentDao().get(obj.getId());

            objP.setId(obj.getId());
            objP.setSection(obj.getSection());
            objP.setImg(obj.getImg());
            objP.setText(obj.getText());

            JSONObject jsonObject = new JSONObject(objP.toJson());

            return jsonObject;
        }else {
            section.getComponents().add(obj);
            HibernateUtils.getCurrentSession().save(obj);
        }
        return new JSONObject(obj.toJson());
    }

    public JSONArray returnComp (JSONObject alo) throws JSONException{

        logger.info("Return Component" + alo);

        Page page = DaoFactory.createPageDao().load(alo.getLong("id"));
        JSONArray result = new JSONArray();

        for (Section section: page.getSections()){
            if (section.getId() == alo.getLong("id")){
                for (Component component : section.getComponents() ){
                    result.put(new JSONObject(((ComponentImpl)component).toJson()));
                }
            }
        }

        logger.info("Results: " + result);
        return result;
    }


    //@IsAuthenticated
   // @HasRole(role = "admin")
    @Transaction
    public void deleteComp(JSONObject component){
        Component comp = (Component)HibernateUtils.getCurrentSession().load(Component.class, component.getLong("id"));
        HibernateUtils.getCurrentSession().delete(comp);

    }


}
