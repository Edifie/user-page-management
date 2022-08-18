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


public class PageService {

    private static final Logger logger = Logger.getLogger(PageService.class);

    @IsAuthenticated
    @Transaction
    @HasRole(role = "admin")
    public JSONObject addPage(JSONObject page){

        PageImpl pimp =  PageImpl.fromJson(page);
        String cookie = SecurityContextProvider.getInstance().getSecuritySessionContext().getRequester();
        UserSession session = (UserSession) HibernateUtils.getCurrentSession().load(UserSession.class, cookie);

        if (pimp.getId() > 0){
            PageImpl pimpP = (PageImpl) DaoFactory.createPageDao().get(pimp.getId());
            pimpP.setTitle(pimp.getTitle());
            pimpP.setRoles(pimp.getRoles());
            pimpP.setUser(pimp.getUser());

            JSONObject jsonObject = new JSONObject(pimpP.toJson());

            return jsonObject;
        }else {
            pimp.setUser(session.getUser());
            HibernateUtils.getCurrentSession().save(pimp);
            return new JSONObject(pimp.toJson());
        }
    }

    @IsAuthenticated
    @Transaction
    public JSONObject loadPage (JSONObject page){
        logger.info("Requesting page");

        Long pimpID = page.getLong("id");
        PageImpl pimpP = (PageImpl) DaoFactory.createPageDao().get(pimpID);

        JSONObject jsonObject = new JSONObject(pimpP.toJson());
        return jsonObject;
    }

    @IsAuthenticated
    @Transaction
    public JSONArray loadAll (JSONObject alo){
        List<Page> pages = DaoFactory.createPageDao().createCriteria().list();
        JSONArray results = new JSONArray();

        for (Page page: pages){
            results.put(new JSONObject(((PageImpl)page).toJson()));
        }

        return results;
    }

    @IsAuthenticated
    @Transaction
    @HasRole(role = "admin")
    public void deletePage(JSONObject page){
        Page page1 = (Page) HibernateUtils.getCurrentSession().load(Page.class, page.getLong("id"));
        HibernateUtils.getCurrentSession().delete(page1);
    }


}
