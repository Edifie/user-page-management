package teste.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet  extends HttpServlet
{

    HttpServletRequest req;
    HttpServletResponse resp;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        doService(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        doService(req,resp);
    }


    protected abstract void doService(HttpServletRequest req,
                                      HttpServletResponse resp)
            throws ServletException, IOException;


    protected void encaminha(String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req,resp);
    }
}
