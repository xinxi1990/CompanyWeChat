package testcase;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class Department extends BasePage {

    public String createPersion = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=";

    @Test
    public void creat(){
        System.out.println("creat");
        Assert.assertEquals(1,1);

    }

    @Test(dependsOnMethods = "creat")
    public void get(){
        System.out.println("get");

    }


}
