package tools;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static tools.LogUntils.log_info;
import static io.restassured.RestAssured.given;

/**
 * 封装常用Requests
 * @author xinxi
 */


public class GetRequest {


    public static Response response;

    public void getRequest(){

    }

    /**
     * 求参数为json数据结构的post请求
     */
    public static Response postRequest(String apiPath,String jsonData){
        Response response = given().
                contentType("application/json;charset=UTF-8").
                body(jsonData).
                when().log().all().post(apiPath.trim());
        return response;

    }


    /**
     * 请求有多个参数结构的post请求
     * 在请求URL中如果有很多个参数,可以用map进行传递,类似于字典
     */
    public static Response postRequestParams(String apiPath, Map map){
        Response response = (Response) given().
                params(map).when().get(apiPath).then().extract();
        return response;
    }


    /**
     * 请求有多个参数结构的post请求
     * 在请求URL中如果有很多个参数,可以用map进行传递,类似于字典
     */
    public static Response postRequestBody(String apiPath, Map map){
        Response response = (Response) given().body(map).when().post(apiPath).then().extract();
        return response;
    }


    /**
     * 无参数的post请求
     */
    public static Response postRequest(String apiPath){
        Response response =  given().when().post(apiPath);
        return response;
    }


    /**
     * 含有cookie的post请求
     */
     public static Response postCookiesRequest(String apiPath, String cookies){
         System.out.println("Cookie = "+cookies);
         Response response = given().contentType("application/json; charset=UTF-8").
                 cookies("web-session", cookies).
                 when().post(apiPath.trim());
         return response;
     }



    /**
     * 通用请求方法
     */
     public static Response CommonRequest(String method,String apiPath,Object paramsMap,Object headerMap){
         RestAssured.useRelaxedHTTPSValidation();
         if (method.endsWith("post")){
             if (paramsMap != null && !paramsMap.equals("") && headerMap != null && !headerMap.equals("") ){
                 response =  given().headers((Map<String, ?>) headerMap).body((Map<String, ?>) paramsMap).
                         when().post(apiPath);
             }else {
                 log_info("post请求,body参数或者header参数必须填写!");
                 response = null;
             }
         }else if (method.endsWith("get")){
             if (paramsMap != null && !paramsMap.equals("") && headerMap != null && !headerMap.equals("") ){
                 response = given().headers((Map<String, ?>) headerMap).params((Map<String, ?>) paramsMap).
                         when().get(apiPath);
             }
         }
        return response;
    }






}
