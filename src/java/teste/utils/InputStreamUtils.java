package teste.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamUtils {

    public static String stream2string(InputStream is) throws IOException {
        InputStreamReader isreader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isreader);

        String line;

        StringBuilder sb =new StringBuilder();
        while((line = reader.readLine())!= null)
        {
            sb.append(line);
        }
        return sb.toString();
    }


}
