package jwt.springjwt.util.response;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import jwt.springjwt.util.data.DataUtil;

import java.io.IOException;


public class RenderUtil {

    public static void renderJson(HttpServletResponse response, ResponseData<DataUtil>jsonObject) {

        try {
            String json = new Gson().toJson(jsonObject);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}