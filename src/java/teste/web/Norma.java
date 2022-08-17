package teste.web;

import org.apache.log4j.Logger;
import teste.utils.HibernateUtils;

import javax.servlet.*;
import java.io.IOException;

public class Norma implements Filter {
    private static final Logger logger = Logger.getLogger(HibernateUtils.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
