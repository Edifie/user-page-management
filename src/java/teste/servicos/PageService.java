package teste.servicos;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import teste.domain.Page;
import teste.domain.PageImpl;
import teste.domain.UserSession;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.SecurityContextProvider;
import teste.servicepack.security.logic.HasRole;
import teste.servicepack.security.logic.IsAuthenticated;
import teste.servicepack.security.logic.Transaction;
import teste.utils.HibernateUtils;

import java.util.List;

/* Same as UserService, Cannot insert with @IsAuthenticated and @HasRole */


public class PageService {

    private static final Logger logger = Logger.getLogger(PageService.class);

    //@IsAuthenticated
    @Transaction
   // @HasRole(role = "PageCreator")

    public JSONObject addPage(JSONObject page){

        PageImpl pageObj =  PageImpl.fromJson(page);

        String cookie = SecurityContextProvider.getInstance().getSecuritySessionContext().getRequester();
        UserSession session = (UserSession) HibernateUtils.getCurrentSession().load(UserSession.class, cookie);

        if (pageObj.getId() > 0){
            PageImpl pageObjP = (PageImpl) DaoFactory.createPageDao().get(pageObj.getId());
            pageObjP.setTitle(pageObj.getTitle());
            pageObjP.setRoles(pageObj.getRoles());
            pageObjP.setUser(pageObj.getUser());

            JSONObject jsonObject = new JSONObject(pageObjP.toJson());

            return jsonObject;
        }else {
            HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
            pageObj.setUser(session.getUser());
            HibernateUtils.getCurrentSession().save(pageObj);
            HibernateUtils.getCurrentSession().getTransaction().commit();
            //return new JSONObject(pageObj.toJson());

        }
        return new JSONObject(pageObj.toJson());
    }

    //@IsAuthenticated
    @Transaction
    public JSONObject loadPage (JSONObject page){
        logger.info("Requesting page");

        Long pageObjID = page.getLong("idPage");
        PageImpl pageObjP = (PageImpl) DaoFactory.createPageDao().get(pageObjID);

        JSONObject jsonObject = new JSONObject(pageObjP.toJson());
        return jsonObject;
    }

   // @IsAuthenticated
    @Transaction
    public JSONArray loadAll (JSONObject alo){
        List<Page> pages = DaoFactory.createPageDao().createCriteria().list();
        JSONArray results = new JSONArray();

        for (Page page: pages){
            results.put(new JSONObject(((PageImpl)page).toJson()));
        }

        return results;
    }

    //@IsAuthenticated
    @Transaction
    @HasRole(role = "admin")
    public void deletePage(JSONObject page){
        Page page1 = (Page) HibernateUtils.getCurrentSession().load(Page.class, page.getLong("idPage"));
        HibernateUtils.getCurrentSession().delete(page1);
    }


}
