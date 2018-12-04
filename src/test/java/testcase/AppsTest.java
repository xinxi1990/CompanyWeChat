package testcase;


import base.BasePage;
import model.Apps;
import model.LoadToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static model.Login.tokenYamlPath;

public class AppsTest extends BasePage {

    public static String accesstoken;
    Apps apps = new Apps();

    @BeforeMethod
    public void beforeMethod(){
        LoadToken.load(new File(tokenYamlPath));
        accesstoken= LoadToken.tokenConfig.getAccess_token();
    }


    @Test
    public void getAppsTest(){
        apps.loadApp();
        apps.putContent("$","access_token", accesstoken);
        apps.getApps(apps.getContent());
    }

    @Test
    public void setAppsTest(){
        apps.loadApp();
        apps.putContent("$","description", "testcase/test");
        apps.setApps(apps.getContent(),accesstoken);
    }

    @Test
    public void getAppsListTest(){
        apps.loadApp();
        apps.putContent("$","access_token", accesstoken);
        apps.getAppsList(apps.getContent());
    }


}
