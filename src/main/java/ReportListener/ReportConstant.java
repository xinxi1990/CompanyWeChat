package reportlistener;
import Tools.DataUntils;

public class ReportConstant {
    // 报告title
    public static String reportName = "Report_" + DataUntils.timeDate() + ".html";
    //生成的路径以及文件名
    public static String rootPath = System.getProperty("user.dir");
    public static final String OUTPUT_FOLDER = "test-output/";
    public static final String REPORT_OUTPUT_FOLDER  = rootPath + "/" + OUTPUT_FOLDER;
    public static final String REPORT_PATH = rootPath + "/" + OUTPUT_FOLDER + reportName;


}
