package teste.servicepack.security;

import java.util.Date;

/**
 * Created by jorgemachado on 23/10/18.
 */
public class SecuritySessionContext
{
    protected String requester;
    protected long startDateMilis = System.currentTimeMillis();
    protected long lastAccessMilis = System.currentTimeMillis();



    public long getStartDateMilis() {
        return startDateMilis;
    }

    public long getLastAccessMilis() {
        return lastAccessMilis;
    }

    public boolean isRegistered(){
        return requester != null;
    }

    public String getRequester()
    {
        lastAccessMilis = System.currentTimeMillis();
        return requester;
    }

    protected void setRequester(String requester) {
        lastAccessMilis = System.currentTimeMillis();
        this.requester = requester;
    }
}
