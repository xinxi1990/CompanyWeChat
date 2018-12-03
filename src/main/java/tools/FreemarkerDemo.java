package tools;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

public class FreemarkerDemo {
    private static final String TEMPLATE_PATH = "src/main/java/templates";
    private static final String CLASS_PATH = "src/main/java/templates";

    public static void main(String[] args) throws IOException {

        makeTemplate();
        //getTemplate(TEMPLATE_PATH,"test.ftl");
    }


    /**
     * 创建java代码
     * @throws IOException
     */
    public void makeJava(){
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.freemark.hello");
            dataMap.put("className", "AutoCodeDemo");
            dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
            // step4 加载模版文件
            Template template = configuration.getTemplate("hello.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "/" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }



    /**
     * 创建模版
     * @throws IOException
     */
    public static void makeTemplate(){
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("A", "A");
            dataMap.put("B", "B");
            dataMap.put("C", "C");
            // step4 加载模版文件
            Template template = configuration.getTemplate("test.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "/" + "testdemo1.yaml");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println(CLASS_PATH + "/" + "testdemo.yaml" + ",文件创建成功!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 从模版中加载方法
     * @throws IOException
     */
    public static void getTemplate(String folder,String tmpPath) throws IOException {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        // step2 获取模版路径
        configuration.setDirectoryForTemplateLoading(new File(folder));
        Template template = configuration.getTemplate(tmpPath);
        if (template.toString().contains("${") && template.toString().contains("}") ){
            System.out.println("检测到有需要$方法");
            String[] temp;
            String delimeter = "\n";  // 指定分割字符
            temp = template.toString().split(delimeter); // 分割字符串
            // 普通 for 循环
            for(int i =0; i < temp.length ; i++){
                String FieldKey = temp[i].split(": ")[0];
                String FieldValue = temp[i].split(": ")[1];
            }
        }
    }


}
