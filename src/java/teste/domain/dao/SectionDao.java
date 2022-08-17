package teste.domain.dao;

import teste.domain.Section;
import teste.domain.SectionImpl;



public class SectionDao extends AbstractDao<Section> {


    @Override
    public Class obtainDomainClass() {
        return SectionImpl.class;
    }

    public SectionDao(){

    }

    private static SectionDao instance = new SectionDao();

    protected static SectionDao getInstance(){
        return instance;
    }
}
