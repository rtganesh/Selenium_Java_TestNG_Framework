package testBase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerTest implements ITestListener {
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentReports extent = ExtentSetup.initExtent();
    public static ExtentTest extentTest;
   

    @Override
    public void onTestStart(ITestResult result) {
       
         extentTest = extent.createTest(result.getName())
                .assignAuthor(System.getProperty("user.name"));
         test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS,"Test Case: "+result.getMethod().getMethodName()+ " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        test.get().log(Status.FAIL,"Test Case: "+result.getMethod().getMethodName()+ " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
        test.get().log(Status.SKIP,"Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
    

}