package Case;

import Base.BasePage;
import Tools.LogUntils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static Case.Login.tokenConfig;
import static Tools.FileUntils.readYaml;
import static io.restassured.RestAssured.given;

public class Apps extends BasePage {

    String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/get";
    String setUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=";
    String getAppsUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/list";
    public static String agentid= "1000002";
    String accesstoken;
    Map tokenMap;

    @BeforeMethod
    public void beforeMethod(){
        tokenMap = readYaml(tokenConfig);
        accesstoken = (String) tokenMap.get("access_token");
    }


    @Test
    public void getApps(){
        Map map = new HashMap();
        map.put("agentid",agentid);
        map.put("access_token",accesstoken);
        Response response = (Response) given().params(map).when().get(getUrl).then().extract();
        Assert.assertEquals(response.path("errcode"),0);
    }


    @Test
    public void setApps(){
        Map map = new HashMap();
        map.put("agentid",agentid);
        map.put("description","test");
        Response response = (Response) given().when().body(map).post(setUrl + accesstoken).then().extract();
        Assert.assertEquals(response.path("errcode"),0);
    }


    @Test
    public void getAppsList(){
        Map map = new HashMap();
        map.put("access_token",accesstoken);
        Response response = (Response) given().params(map).when().get(getAppsUrl).then().extract();
        Assert.assertEquals(response.path("errcode"),0);
    }



}
