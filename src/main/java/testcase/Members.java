//package testcase;
//
//import base.BasePage;
//import io.restassured.RestAssured;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import tools.GetRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static tools.FileUntils.readYaml;
//
//public class Members extends BasePage {
//
//    public static String creteApi = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=";
//    String accesstoken;
//    Map tokenMap;
//
//    @BeforeMethod
//    public void beforeMethod(){
//        tokenMap = readYaml(tokenConfig);
//        accesstoken = (String) tokenMap.get("access_token");
//    }
//
//    /**
//     * 添加成员
//     */
//    @Test
//    public void creat(){
//        Map map = new HashMap();
//        map.put("userid","xinxi");
//        map.put("name","xinxi");
//        map.put("department","xinxi");
//        Response response = GetRequest.postRequestBody(creteApi + accesstoken,map);
//        System.out.println(response.body().prettyPrint());
//        Assert.assertEquals(response.path("errcode"),0);
//
//    }
//
//}
