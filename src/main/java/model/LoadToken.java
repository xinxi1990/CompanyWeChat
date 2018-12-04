package model;

import java.io.File;
import java.util.List;
import config.TokenConfig;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


/**
 * 加载登录token
 * @author xinxi
 */

public class LoadToken {

    public static TokenConfig tokenConfig = null;

    public static void load(File file){
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        try {
            tokenConfig = mapper.readValue(file, TokenConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<TokenConfig> readFromYAML(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<TokenConfig> data = mapper.readValue(
                new File(filePath),
                new TypeReference<List<TokenConfig>>(){}
        );
        return data;
    }


    public static void writeToken(String filePath){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(filePath), tokenConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}



