package Case;

import Tools.LogUntils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import static Tools.DataUntils.getSecondTimestamp;
import static Tools.FileUntils.readYaml;
import static Tools.FileUntils.writeYaml;
import static io.restassured.RestAssured.given;

public class Login {

    public static String tokenConfig =  System.getProperty("user.dir") + "/src/main/java/Config/token.yaml";
    public Boolean expires;
    public int expirestime;
    public String access_token;


    public void login(){
        if (checkToken()){
            getToken();
        }

    }


    /**
     * 获取应用的token值
     */
    private void getToken(){
        Map tokenMap = readYaml(tokenConfig);
        tokenMap.get("corpsecret");
        RestAssured.useRelaxedHTTPSValidation();
        String url = (String) tokenMap.get("tokenApi");
        Map map = new HashMap();
        map.put("corpid",tokenMap.get("corpid"));
        map.put("corpsecret",tokenMap.get("corpsecret"));
        Response response = (Response) given().params(map).when().get(url).then().extract();
        if (response.path("errcode").equals(0)){
            LogUntils.log_info("获取token成功!");
            map.put("tokenApi",url);
            saveToken(map,response);
        }else {
            LogUntils.log_info("获取token失败!");
        }
    }





    /**
     * 保存token
     * @param tokenMap
     * @param response
     */
    private void saveToken(Map tokenMap,Response response){
        expirestime = ((Integer) response.path("expires_in")) + getSecondTimestamp();
        access_token = response.path("access_token");
        tokenMap.put("expirestime",expirestime);
        tokenMap.put("access_token",access_token);
        writeYaml(tokenMap,tokenConfig);
    }


    /**
     * 检查token是否过期
     * @return
     */
    private boolean  checkToken(){
        Map tokenMap = readYaml(tokenConfig);
        int expirestime =  Integer.parseInt(String.valueOf(tokenMap.get("expirestime")));
        if (tokenMap.containsKey("expirestime")){
            if (getSecondTimestamp() > expirestime){
                LogUntils.log_info("token已经过期!");
                expires = true;
            }else {
                LogUntils.log_info("token没有过期!");
                expires = false;
            }
        }
        return expires;
    }



    public static void main(String[] args) {
        new Login().login();
    }

}
