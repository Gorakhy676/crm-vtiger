package com.comcast.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.webdriverutility.UtilityClassObject;

public class ListnerImplementationClass implements ITestListener,ISuiteListener{
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	 public static ExtentTest test;

	 	@Override
		public void onStart(ISuite suite)
		{
		System.out.println(" Report Configuration");	
		
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM TEST SUITE RESULT");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add Env information & create Test
				 report=new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows 11");
				report.setSystemInfo("Environment", "QA");
				report.setSystemInfo("Author", "Gorakh Yadav");
		}
		
	 	@Override
		public void onFinish(ISuite suite)
		{
			System.out.println(" Report Backup");
			report.flush();
		}
		
	 	@Override
		public void onTestStart(ITestResult result)
		{
			System.out.println("====="+result.getMethod().getMethodName()+"====Start======");
		    test = report.createTest(result.getMethod().getMethodName());
		    UtilityClassObject.setTest(test);
		    test.log(Status.INFO, result.getMethod().getMethodName()+"==> STARTED <===");

		}
		
	 	public void onTestSucess(ITestResult result)
        {
        	System.out.println("====="+result.getMethod().getMethodName()+"====End======");	
        	 test.log(Status.PASS, result.getMethod().getMethodName()+"==> COMPLETED <===");
		}
        
        
        @Override
        public void onTestFailure(ITestResult result)
        {
        	String testName=result.getMethod().getMethodName();
        	

    		//step 1: create an object to eventfirirng webdriver
            TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;
    		
    		//step-2: use getScreenshotAs method to get file type of screenshot
            String filepath = eDriver.getScreenshotAs(OutputType.BASE64);    		
    		//step 3: store screenshot in local drive
    		String time=new Date().toString().replace(" ","_").replace(":", "_");

            test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
            test.log(Status.FAIL, result.getMethod().getMethodName()+"==> FAILED <===");
    		
		}
        
        public void onTestSkip(ITestResult result)
        {
        	System.out.println("====="+result.getMethod().getMethodName()+"==========");
			
      	}
        
        
        
}
