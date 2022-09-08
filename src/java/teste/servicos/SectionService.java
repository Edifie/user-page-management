package teste.servicos;


import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import teste.domain.*;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.logic.HasRole;
import teste.servicepack.security.logic.IsAuthenticated;
import teste.servicepack.security.logic.Transaction;
import teste.utils.HibernateUtils;


public class SectionService {

    private static final Logger logger = Logger.getLogger(SectionService.class);



    //@IsAuthenticated
    @Transaction
    //@HasRole(role = "admin")
    public JSONObject addSection(JSONObject section){
        logger.info("Section to add");


        long idPage = section.getLong("idPage");
        Page page = DaoFactory.createPageDao().load(idPage);

        SectionImpl obj = new SectionImpl();
        obj.setTitle(section.getString("title"));


        if (obj.getId() > 0){
            SectionImpl objP = (SectionImpl) DaoFactory.createSectionDao().get(obj.getId());
            objP.setId(obj.getId());
            objP.setTitle(obj.getTitle());
            objP.setPage(obj.getPage());

            JSONObject jsonObject = new JSONObject(objP.toJson());

            return jsonObject;
        }else {
            DaoFactory.createSectionDao().save(obj);

            obj.setPage(page);
            page.getSections().add(obj);
        }

        return new JSONObject(obj.toJson());

    }



    //@IsAuthenticated
    @Transaction
    public JSONArray returnAll (JSONObject alo) throws JSONException{

        Page page = DaoFactory.createPageDao().load(alo.getLong("id"));
        JSONArray results = new JSONArray();

        for (Section section: page.getSections()){
            for (Component component:section.getComponents()){
                results.put(new JSONObject(((ComponentImpl)component).toJson()));
            }
        }
        return results;
    }




    //@IsAuthenticated
    @Transaction
    //@HasRole(role = "admin")
    public void deleteSection (JSONObject section){
        Section section1 = (Section) HibernateUtils.getCurrentSession().load(Section.class, section.getLong("idSection"));
        HibernateUtils.getCurrentSession().delete(section1);
    }

}
