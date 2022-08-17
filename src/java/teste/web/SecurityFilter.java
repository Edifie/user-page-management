package teste.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;
import teste.pluginSession.*;

public class  SecurityFilter implements Filter {

    @Override
    public void init (FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        openSession (servletRequest,filterChain, response);
    }

    private void openSession(ServletRequest servletRequest, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException{
        RegisterSession plugin = null;

        Logger logger = Logger.getLogger(SecurityFilter.class);
        logger.info("ASDASD");

        try {

            plugin = (RegisterSession) PluginFactory.getPlugin(RegisterSession.class);
            logger.info("ALOOOOO");
            plugin.openSession(servletRequest,filterChain,response);
            logger.info("ALOO222");
        } catch (ClassNotFoundException e){
            logger.info("1");
            e.printStackTrace();
        }catch (IllegalAccessException e){
            logger.info("2");
            e.printStackTrace();
        }catch (InstantiationException e){
            logger.info("3");
            e.printStackTrace();
        }

    }


    @Override
    public void destroy() {

    }
}
