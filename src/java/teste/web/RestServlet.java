package teste.web;


import org.apache.log4j.Logger;
import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import teste.utils.InputStreamUtils;

public class RestServlet extends AbstractServlet
{
    private static final Logger logger = Logger.getLogger(RestServlet.class);

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getContentType().equals("application/json"))
        {
            String json = InputStreamUtils.stream2string(req.getInputStream());

            JSONObject jsonObject = new JSONObject(json);

            System.out.println(jsonObject.toString());

            String servico = jsonObject.getString("servico");
            String op = jsonObject.getString("op");

            try {
                Object servicoObj = Class.forName("teste.servicos." + servico).newInstance();
                Method m = servicoObj.getClass().getMethod(op,new Class[]{JSONObject.class});
                Object ob =  m.invoke(servicoObj,jsonObject.getJSONObject("data"));

                JSONObject response = new JSONObject();
                response.put("execute",true);

                response.put("data",ob);

                resp.getWriter().write(response.toString());

            } catch (InstantiationException e) {
                JSONObject response = new JSONObject();
                response.put("execute",false);
                response.put("error:",e.toString());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //resp.sendError(401);

        }
        else
            encaminha("/home.jsp");
    }


}