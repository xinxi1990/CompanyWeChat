package model;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.LoginConfig;

public class LoadLogin {

    public static LoginConfig loginConfig = null;

    public static void load(File file){
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        try {
            loginConfig = mapper.readValue(file, LoginConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
