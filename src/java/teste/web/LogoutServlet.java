package teste.web;

import org.apache.log4j.Logger;
import teste.servicos.LogoutService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends AbstractServlet{

    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Logout");

        HttpSession session = req.getSession();
        LogoutService logoutService = new LogoutService();

        logoutService.logout(null);
        session.setAttribute("isLoggedIn", false);

        String encodedURL = resp.encodeRedirectUrl("http://localhost:8080/TrabalhoES/login.do");
        resp.sendRedirect(encodedURL);
    }
}
