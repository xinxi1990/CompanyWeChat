package Base;

import Case.Login;
import org.apache.log4j.Level;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static Tools.LogUntils.initLogger;

public class BasePage {


    @BeforeClass
    public void beforeClass(){
        initLogger().setLevel(Level.ALL);
        new Login().login();
    }


    @AfterClass
    public void afterClass(){

    }


}
