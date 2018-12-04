package model;

import base.BasePage;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import config.DomainConfig;
import io.restassured.http.ContentType;
import tools.FileUntils;
import tools.GetRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
//import static model.Apps.agentid;
import static io.restassured.RestAssured.given;
import static model.Login.tokenYamlPath;

public class Messages extends BasePage {

    public static String sendTextUrl = DomainConfig.Domian + "/cgi-bin/message/send?access_token=";


    public DocumentContext messages;

    public void loadApp(){
        messages= FileUntils.readJson("/messages.json");
    }

    public void setContent(String key, Object value){
        messages.set(JsonPath.compile(key), value);
    }

    public void addContent(JsonPath jsonPath, Object value){
        messages.add(jsonPath, value);
    }

    public void putContent(String path, String key, String value){
        messages.put(path, key, value);
    }

    public String getContent(){
        return messages.jsonString();
    }




    public Response sendText(String body,String accesstoken){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when().post(sendTextUrl + accesstoken)
                .then().statusCode(200).extract().response();

    }

}
