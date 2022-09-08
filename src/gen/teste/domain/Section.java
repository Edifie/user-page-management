package teste.domain;
// Generated Sep 7, 2022 6:17:29 PM by Hibernate Tools 3.2.0.b9


import java.util.HashSet;
import java.util.Set;

/**
 * Section generated by hbm2java
 */
public abstract class Section  implements java.io.Serializable {


     private long id;
     private String title;
     private Page page;
     private Set<ComponentImpl> components = new HashSet<ComponentImpl>(0);

    public Section() {
    }

   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public Page getPage() {
        return this.page;
    }
    
    public void setPage(Page page) {
        this.page = page;
    }
    public Set<ComponentImpl> getComponents() {
        return this.components;
    }
    
    public void setComponents(Set<ComponentImpl> components) {
        this.components = components;
    }




}


