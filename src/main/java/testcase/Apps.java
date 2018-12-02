package testcase;

import base.BasePage;
import config.LoadToken;
import tools.GetRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static testcase.Login.tokenYamlPath;


public class Apps extends BasePage {

    public static String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/get";
    public static String setUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=";
    public static String getAppsUrl = "https://qyapi.weixin.qq.com/cgi-bin/agent/list";
    public static String agentid= "1000002";
    public static String accesstoken;

    @BeforeMethod
    public void beforeMethod(){
        LoadToken.load(new File(tokenYamlPath));
        accesstoken= LoadToken.tokenConfig.getAccess_token();
    }


    @Test
    public void getApps(){
        Map map = new HashMap();
        map.put("agentid",agentid);
        map.put("access_token",accesstoken);
        Response response = GetRequest.postRequestParams(getUrl,map);
        Assert.assertEquals(response.path("errcode"),0);
    }


    @Test
    public void setApps(){
        Map map = new HashMap();
        map.put("agentid",agentid);
        map.put("description","test");
        Response response = GetRequest.postRequestBody(setUrl + accesstoken,map);
        Assert.assertEquals(response.path("errcode"),0);
    }


    @Test
    public void getAppsList(){
        Map map = new HashMap();
        map.put("access_token",accesstoken);
        Response response = GetRequest.postRequestParams(getAppsUrl,map);
        Assert.assertEquals(response.path("errcode"),0);
    }



}
