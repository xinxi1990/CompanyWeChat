package reportlistener;

import org.testng.Reporter;

import java.util.Calendar;

public class ReportUtil {

    private static String reportName = ReportConstant.reportName;
    private static String splitTimeAndMsg = "===";


    public static void log(String msg) {
        long timeMillis = Calendar.getInstance().getTimeInMillis();
        Reporter.log(timeMillis + splitTimeAndMsg + msg, true);
    }

    public static String getReportName() {
        return reportName;
    }

    public static String getSpiltTimeAndMsg() {
        return splitTimeAndMsg;
    }

    public static void setReportName(String reportName) {
        if(reportlistener.StringUtil.isNotEmpty(reportName)){
            ReportUtil.reportName = reportName;
        }
    }
}
