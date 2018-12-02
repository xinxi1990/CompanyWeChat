package tools;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class LogUntils {

    public static Logger logger;

    /**
     * 初始化logger
     */
    public static Logger initLogger(){
        logger = Logger.getLogger("CompanyWeChat");
        return logger;
    }

    /**
     * info日志
     */
    public static void log_info(String text){
        Reporter.log(text);
        logger.info(text);
    }

    /**
     * debug日志
     */
    public static void log_debug(String text){
        Reporter.log(text);
        logger.info(text);
    }

    /**
     * debug日志
     */
    public static void log_trace(String text){
        Reporter.log(text);
        logger.trace(text);
    }


}