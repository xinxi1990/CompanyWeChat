package base;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import model.Login;
import io.restassured.RestAssured;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static tools.LogUntils.initLogger;


public class BasePage {

    @BeforeClass
    public void beforeClass() throws InterruptedException, FileNotFoundException {
        RestAssured.useRelaxedHTTPSValidation();
        PrintStream ps = new PrintStream(new File("testcase.test.log"));
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        // 打印全局log日志
        initLogger().setLevel(Level.ALL);
        new Login().login();
    }

    @AfterClass
    public void afterClass(){

    }


}
