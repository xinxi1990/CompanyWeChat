package Case;

import Base.BasePage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static Case.Apps.agentid;
import static Case.Login.tokenConfig;
import static Tools.FileUntils.readYaml;
import static io.restassured.RestAssured.given;

public class Messages extends BasePage {

    String sendTextUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
    String accesstoken;
    Map tokenMap;

    @BeforeMethod
    public void beforeMethod(){
        tokenMap = readYaml(tokenConfig);
        accesstoken = (String) tokenMap.get("access_token");
    }


    @Test
    public void sendText(){
        Map textMap = new HashMap();
        textMap.put("content","1111");
        textMap.put("safe",0);
        Map map = new HashMap();
        map.put("agentid", agentid);
        map.put("msgtype", "text");
        map.put("touser","@all");
        map.put("text",textMap);
        Response response = (Response) given().body(map).when().post(sendTextUrl + accesstoken ).then().extract();
        Assert.assertEquals(response.path("errcode"),0);



    }

}
