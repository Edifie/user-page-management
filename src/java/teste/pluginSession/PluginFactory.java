package teste.pluginSession;

import java.io.IOException;
import java.util.Properties;

public class PluginFactory
{
    static Properties properties = new Properties();
    static
    {
        try {
            properties.load(PluginFactory.class.getResourceAsStream("/plugins.properties"));
        } catch (IOException e) {
            System.out.println("ASADS");
            e.printStackTrace();
        }
    }


    public static Object getPlugin(Class iface) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        String classNameVerdadeiro = properties.getProperty(iface.getName());
        return Class.forName(classNameVerdadeiro).newInstance();
    }
}
