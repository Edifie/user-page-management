package teste.domain.dao;

import teste.domain.Page;
import teste.domain.PageImpl;
import teste.domain.UserSessionImpl;

public class PageDao extends AbstractDao<Page>{

    private PageDao(){

    }

    private static PageDao instance = new PageDao();

    protected static PageDao getInstance(){
        return instance;
    }

    @Override
    public Class obtainDomainClass() {
        return PageImpl.class;
    }
}
