package teste.domain;
// Generated Sep 7, 2022 6:17:29 PM by Hibernate Tools 3.2.0.b9



/**
 * UserSession generated by hbm2java
 */
public abstract class UserSession  implements java.io.Serializable {


     private String cookie;
     private User user;

    public UserSession() {
    }

	
    public UserSession(String cookie) {
        this.cookie = cookie;
    }
   
    public String getCookie() {
        return this.cookie;
    }
    
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}


