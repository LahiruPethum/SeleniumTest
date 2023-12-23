package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    public void onStart(ITestContext arg){
        System.out.println("Start Test execution......... "+arg.getName());
    }

    public void onFinish(ITestContext arg){
        System.out.println("Finish Test execution........."+arg.getName());

    }

    public void onTestStart(ITestResult arg){
        System.out.println("Start test .........." +arg.getName());
    }

    public void onTestSkipped(ITestResult arg){
        System.out.println("Skipped test .......... "+arg.getName());
    }

    public void onTestFailure(ITestResult arg){
        System.out.println("Failed test .......... "+arg.getName());
    }


}
