package reportlistener;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xinxi
 * 重写TestListenerAdapter监听
 * 增加失败截图
 */

public class AutoTestListener extends TestListenerAdapter {

    private static File classpathRoot = new File(reportlistener.ReportConstant.rootPath);
    public static int Success = 0;
    public static int Failure = 0;

    @Override
    public void onTestSuccess(ITestResult tr) {
        // TODO Auto-generated method stub
        reportlistener.TestngRetry.resetRetryCount();
        super.onTestSuccess(tr);
        Success ++;

    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        Failure ++;
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        saveResult(tr);
        super.onTestSkipped(tr);

    }


    private void saveResult(ITestResult tr) {
//        Throwable throwable = tr.getThrowable();
//        if (null == throwable) {
//            return;
//        }
//         String imgPath = WebdriverUtil.captureEntirePageScreenshot();
//         log.error("用例执行错误截图：" + imgPath);
//         Reporter.setCurrentTestResult(tr);
//         Reporter.log("path path path path");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);

        // List of test results which we will delete later
        ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
        // collect all id's from passed test
        Set<Integer> passedTestIds = new HashSet<Integer>();
        for (ITestResult passedTest : testContext.getPassedTests()
                .getAllResults()) {
            // logger.info("PassedTests = " + passedTest.getName());
            passedTestIds.add(getId(passedTest));
        }

        Set<Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests()
                .getAllResults()) {
            // logger.info("failedTest = " + failedTest.getName());
            // id = class + method + dataprovider
            int failedTestId = getId(failedTest);

            // if we saw this test as a failed test before we mark as to be
            // deleted
            // or delete this failed test if there is at least one passed
            // version
            if (failedTestIds.contains(failedTestId)
                    || passedTestIds.contains(failedTestId)) {
                testsToBeRemoved.add(failedTest);
            } else {
                failedTestIds.add(failedTestId);
            }
        }

        // finally delete all tests that are marked
        for (Iterator<ITestResult> iterator =

             testContext.getFailedTests().getAllResults().iterator(); iterator
                     .hasNext();) {
            ITestResult testResult = iterator.next();

            if (testsToBeRemoved.contains(testResult)) {
                // logger.info("Remove repeat Fail Test: " +
                // testResult.getName());
                iterator.remove();
            }
        }

    }

    private int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = id + result.getMethod().getMethodName().hashCode();
        id = id
                + (result.getParameters() != null ? Arrays.hashCode(result
                .getParameters()) : 0);
        return id;
    }

    /**
     * 模拟器获取用例失败截图Base64
     * @param tr
     * @throws IOException
     */
    public void simTakeScreenShotBase64(ITestResult tr) throws IOException, InterruptedException {
        String picPath = classpathRoot + String.format("/screenShot/Fail_%s.png", getDateFile(tr));
        String screenCmd = String.format("xcrun simctl io booted screenshot %s", picPath);
        Runtime.getRuntime().exec(screenCmd);
        Reporter.setCurrentTestResult(tr);
        Thread.sleep(500);
        String pic = String.format("<img src=\"data:image/jpg;base64,%s\"  height=\"500\" width=\"300\"/>",GetImageStr(picPath));
        Reporter.log(pic);
    }

    /**
     * 图片转化成base64字符串
     * @param picPath 图片地址
     * @throws IOException
     */
    private static String GetImageStr(String picPath)
    {
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(picPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data); //返回Base64编码过的字节数组字符串
    }


    /**
     时间为后缀的路径
     @param tr
     */
    private String  getDateFile(ITestResult tr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String mDateTime = formatter.format(new Date());
        String fileName = mDateTime + "_" + tr.getName();
        return fileName;
    }


}
