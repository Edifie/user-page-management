package teste.pluginSession;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RegisterSession
{
    public void openSession(ServletRequest servletRequest, FilterChain filterChain, HttpServletResponse response) throws IOException, ServletException;
}
