package tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射获取方法
 * @author xinxi
 */


public class HotLoad {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clz = Class.forName("tools.FileUntils");
        Object o = clz.newInstance();
        Method m = clz.getMethod("currentTime");
        System.out.println(m.invoke(o));

        Object o1 = clz.newInstance();
        Method m1 = clz.getMethod("readYaml",String.class);
        System.out.println(m1.invoke(o1,"./src/main/java/yaml/login.yaml"));
    }


}
