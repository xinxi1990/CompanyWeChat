package model;

import base.BasePage;
import java.util.Map;

import io.restassured.http.ContentType;
import org.testng.Assert;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import config.DomainConfig;
import tools.FileUntils;
import tools.GetRequest;
import io.restassured.response.Response;
import com.jayway.jsonpath.DocumentContext;

import static io.restassured.RestAssured.given;


public class Apps {

    public static String getUrl = DomainConfig.Domian + "/cgi-bin/agent/get";
    public static String setUrl = DomainConfig.Domian + "/cgi-bin/agent/set?access_token=";
    public static String getAppsUrl = DomainConfig.Domian + "/cgi-bin/agent/list";

    public DocumentContext app;

    public void loadApp(){
        app= FileUntils.readJson("/apps.json");
    }

    public void setContent(String key, Object value){
        app.set(JsonPath.compile(key), value);
    }

    public void addContent(JsonPath jsonPath, Object value){
        app.add(jsonPath, value);
    }

    public void putContent(String path, String key, String value){
        app.put(path, key, value);
    }

    public String getContent(){
        return app.jsonString();
    }


    public Response getApps(String body){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when().post(getUrl)
                .then().statusCode(200).extract().response();
    }


    public Response setApps(String body,String accesstoken){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when().post(setUrl + accesstoken)
                .then().statusCode(200).extract().response();
    }


    public Response getAppsList(String body){
        return given().contentType(ContentType.JSON)
                .body(body)
                .when().post(getAppsUrl)
                .then().statusCode(200).extract().response();
    }



}
