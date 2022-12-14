package teste.domain;
// Generated Sep 7, 2022 6:17:29 PM by Hibernate Tools 3.2.0.b9


import java.util.HashSet;
import java.util.Set;

/**
 * Page generated by hbm2java
 */
public abstract class Page  implements java.io.Serializable {


     private long id;
     private String title;
     private String roles;
     private User user;
     private Set<Section> sections = new HashSet<Section>(0);

    public Page() {
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
    public String getRoles() {
        return this.roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Set<Section> getSections() {
        return this.sections;
    }
    
    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }




}


