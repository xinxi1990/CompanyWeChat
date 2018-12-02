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
import static testcase.Apps.agentid;
import static testcase.Login.tokenYamlPath;

public class Messages extends BasePage {

    String sendTextUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
    String accesstoken;

    @BeforeMethod
    public void beforeMethod(){
        LoadToken.load(new File(tokenYamlPath));
        accesstoken= LoadToken.tokenConfig.getAccess_token();
    }


    @Test
    public void sendText(){
        Map textMap = new HashMap();
        textMap.put("content",System.currentTimeMillis());
        textMap.put("safe",0);
        Map map = new HashMap();
        map.put("agentid", agentid);
        map.put("msgtype", "text");
        map.put("touser","@all");
        map.put("text",textMap);
        Response response = GetRequest.postRequestBody(sendTextUrl + accesstoken,map);
        Assert.assertEquals(response.path("errcode"),0);



    }

}
