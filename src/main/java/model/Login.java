package model;

import config.TokenConfig;
import tools.LogUntils;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static Tools.DataUntils.getSecondTimestamp;
import static io.restassured.RestAssured.given;

public class Login {

    public static String rootPath = System.getProperty("user.dir");
    public static String loginYamlPath = rootPath + "/src/main/java/config/login.yaml";
    public static String tokenYamlPath =  rootPath + "/src/main/java/config/token.yaml";

    public void login() throws InterruptedException {
        if (checkToken()){
            getToken();
        }
    }

    /**
     * 获取应用的token值
     */
    private static void getToken(){
        Map tokenMap = new HashMap();
        LoadLogin.load(new File(loginYamlPath));
        tokenMap.put("corpid",LoadLogin.loginConfig.getCorpid());
        tokenMap.put("corpsecret",LoadLogin.loginConfig.getCorpsecret());
        Response response = (Response) given().params(tokenMap).when().get(LoadLogin.loginConfig.getLoginapi()).then().extract();
        if (response.path("errcode").equals(0)){
            LogUntils.log_info("获取token成功!");
            saveToken(response);
        }else {
            LogUntils.log_info("获取token失败!");
        }
    }


    /**
     * 检查token是否过期
     * @return
     */
    private static boolean  checkToken() throws InterruptedException {
        boolean expires = true;
        File file=new File(tokenYamlPath);
        if (file.exists()){
            LoadToken.load(new File(tokenYamlPath));
            String token = LoadToken.tokenConfig.getAccess_token();
            if (token == null){
                getToken();
            }else {
                int expirestime = Integer.parseInt(LoadToken.tokenConfig.getExpirestime());
                if (getSecondTimestamp() > expirestime){
                    LogUntils.log_info("token已经过期!");
                    getToken();
                    expires = true;
                }else {
                    LogUntils.log_info("token没有过期!");
                    expires = false;
                }
            }
        }else {
            getToken();
        }
        return expires;
    }


    /**
     * 保存新token
     * @param response
     */
    private static void saveToken(Response response){
        int Expirestime = response.path("expires_in");
        LoadToken.tokenConfig = new TokenConfig();
        LoadToken.tokenConfig.setAccess_token((String) response.path("access_token"));
        LoadToken.tokenConfig.setExpirestime(String.valueOf( Expirestime  + getSecondTimestamp()));
        LoadToken.writeToken(tokenYamlPath);
        LogUntils.log_info("新token写入成功!");

    }

    public static void main(String[] args) {
        getToken();
    }

}
