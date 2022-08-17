package teste.pluginSession;


import teste.domain.User;
import teste.domain.UserSession;
import teste.domain.UserSessionImpl;
import teste.servicepack.security.SecurityContextProvider;
import teste.utils.HibernateUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerRegisterSession implements RegisterSession {
    @Override
    public void openSession(ServletRequest servletRequest, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        SecurityContextProvider.getInstance().registerSessionContext(request.getSession().getId());

        UserSession session;
        HibernateUtils.getCurrentSession().beginTransaction();
        User u = null;
        try {
            session = (UserSession) HibernateUtils.getCurrentSession().load(UserSession.class, request.getSession().getId());
            if(session == null)
            {
                session = new UserSessionImpl();
                session.setCookie(request.getSession().getId());
                HibernateUtils.getCurrentSession().save(session);


            }
            u = session.getUser();
        }catch(Exception e)
        {

            session = new UserSessionImpl();
            session.setCookie(request.getSession().getId());
            HibernateUtils.getCurrentSession().save(session);
        }


        session.setUser(u);

        HibernateUtils.getCurrentSession().getTransaction().commit();

        filterChain.doFilter(servletRequest,response);
    }
}
