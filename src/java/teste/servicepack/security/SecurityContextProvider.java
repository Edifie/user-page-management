package teste.servicepack.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorgemachado on 23/10/18.
 */
public class SecurityContextProvider
{

    private static SecurityContextProvider singletonInstance = new SecurityContextProvider();

    private Map<Thread,SecuritySessionContext> sessions = new HashMap<Thread,SecuritySessionContext>();

    private SecurityContextProvider(){}

    public static SecurityContextProvider getInstance()
    {
        return singletonInstance;
    }


    public SecuritySessionContext getSecuritySessionContext()
    {
        SecuritySessionContext securitySessionContext = sessions.get(Thread.currentThread());
        if(securitySessionContext == null)
            throw new RuntimeException("SecuritySessionContext not registered");
        return securitySessionContext;
    }

    /**
     * O Requester pode ser um cookie
     * @param requester
     * @return
     */
    public synchronized SecuritySessionContext registerSessionContext(String requester)
    {
        if(requester == null)
        {
            throw new RuntimeException("SecuritySessionContext must be registered with A VALID requester");
        }
        SecuritySessionContext securitySessionContext = new SecuritySessionContext();
        securitySessionContext.setRequester(requester);
        sessions.put(Thread.currentThread(),securitySessionContext);
        return securitySessionContext;
    }


    public void unregisterSecuritySessionContext()
    {
        SecuritySessionContext securitySessionContext = getSecuritySessionContext();
        securitySessionContext.setRequester(null);
    }



}
