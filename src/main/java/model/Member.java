package model;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import tools.FileUntils;

/***
 * 组织模块
 * @author xinxi
 */

public class Member {

    public DocumentContext member;

    public void loadMember(){
        member= FileUntils.readJson("/member.json");
    }

    public void setContent(String key, Object value){
        member.set(JsonPath.compile(key), value);
    }

    public void addContent(JsonPath jsonPath, Object value){
        member.add(jsonPath, value);
    }

    public void putContent(String path, String key, String value){
        member.put(path, key, value);
    }

    public String getContent(){
        return member.jsonString();
    }



}
