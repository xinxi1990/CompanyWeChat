//package testcase;
//
//
//import base.BasePage;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.util.HashMap;
//import java.util.Map;
//import static testcase.Login.tokenConfig;
//import static tools.FileUntils.readYaml;
//import static io.restassured.RestAssured.given;
//
//
//public class Person extends BasePage {
//
//    public String createPersion = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=";
//
//    String accesstoken;
//    Map tokenMap;
//
//    @BeforeMethod
//    public void beforeMethod(){
//        tokenMap = readYaml(tokenConfig);
//        accesstoken = (String) tokenMap.get("access_token");
//    }
//
//    @Test
//    public void create(){
//        Map map = new HashMap();
//        map.put("userid","zhangsan");
//        map.put("name","张三");
//    }
//
//
//}
