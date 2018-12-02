package config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

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


    public static void write(String filePath){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(filePath), tokenConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNull(String filePath){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File(filePath), new TokenConfig());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}



