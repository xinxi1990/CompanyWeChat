package testcase;

import base.BasePage;
import model.Apps;
import model.LoadToken;
import model.Messages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static model.Login.tokenYamlPath;

public class MessagesTest extends BasePage {

    public static String accesstoken;
    Messages messages = new Messages();
    String textStr = String.valueOf(System.currentTimeMillis());

    @BeforeMethod
    public void beforeMethod(){
        LoadToken.load(new File(tokenYamlPath));
        accesstoken= LoadToken.tokenConfig.getAccess_token();
    }

    @Test
    public void sendTextTest(){
        messages.loadApp();
        messages.putContent("$.text","content", textStr);
        messages.sendText(messages.getContent(),accesstoken);
    }



}
