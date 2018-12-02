package testcase;

import base.BasePage;
import config.LoadToken;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static testcase.Login.tokenYamlPath;

public class Address  extends BasePage {

    public String create = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=";
    String accesstoken;

    @BeforeMethod
    public void beforeMethod(){
        LoadToken.load(new File(tokenYamlPath));
        accesstoken= LoadToken.tokenConfig.getAccess_token();
    }

    @Test
    public void create(){
        Map map = new HashMap();
        map.put("userid","zhangsan");
        map.put("name","张三");
        Response response = (Response) given().body(map).when().post(create + accesstoken ).then().extract();
        Assert.assertEquals(response.path("errcode"),0);
    }


}
