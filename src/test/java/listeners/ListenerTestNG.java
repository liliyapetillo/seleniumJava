package listeners;

import demoTestStore.Base;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestNG extends Base implements ITestListener {

    public void onTestFailure(ITestResult result) {
        System.out.println("Test has failed and this message is from the ITestListner interface");
        try {
            FailedScreenshot(result.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test has passed from ITestListner interface!");
    }
}
