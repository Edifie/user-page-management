package teste.web;

import org.apache.log4j.Logger;
import teste.domain.User;
import teste.servicos.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends AbstractServlet
{
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginService = new LoginService();


        String username = req.getParameter("username");
        String password = req.getParameter("password");

        logger.info("Username: " + username);
        logger.debug("Password" + password);



        User user = null;
        try {
            user = loginService.checkLogin(username,password);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IOException(e.toString(),e);
        }

        if (user!= null){

            String roles = user.getRoles();

            logger.info("Username: " + username);

            if (roles !=null){
                HttpSession session = req.getSession();

                req.setAttribute("userLoggedIn", user);
                session.setAttribute("username", username);
                session.setAttribute("roles",roles);

                String encodedURL = resp.encodeRedirectUrl("home.do");
                resp.sendRedirect(encodedURL);
            }else {
                HttpSession session = req.getSession();

                req.setAttribute("userLoggedIn", user);
                session.setAttribute("username", username);
                session.setAttribute("roles", "normal");

                String encodedURL = resp.encodeRedirectUrl("home.do");
                resp.sendRedirect(encodedURL);
            }
        }else {
            String encodedURL = resp.encodeRedirectUrl("http://localhost:8080/war/login.do?wrong_password");
            resp.sendRedirect(encodedURL);
        }
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}