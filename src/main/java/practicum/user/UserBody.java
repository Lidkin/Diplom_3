package practicum.user;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;


public class UserBody {

    Gson gson = new Gson();

    public String UserRegisterBody(String email, String password, String name) {
        Map<String, String> registerBody = new HashMap<>();
        registerBody.put("email", email);
        registerBody.put("password", password);
        registerBody.put("name", name);
        return gson.toJson(registerBody);
    }

    public String UserLoginBody(String email, String password){
        Map<String, String> registerBody = new HashMap<>();
        registerBody.put("email", email);
        registerBody.put("password", password);
        return gson.toJson(registerBody);
    }
}
