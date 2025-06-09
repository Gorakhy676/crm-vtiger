package com.comcast.crm.generic.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.UtilityClassObject;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class BaseClass {

	public DataBaseUtility dblib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public JavaUtility jlib = new JavaUtility();
	public ExcelUtility elib = new ExcelUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;


	@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void configBS() throws Throwable {
		System.out.println("connect to DB,Report Config==");
		dblib.getDbconnection();
		
	}

//	@Parameters("BROWSER")
//	@BeforeClass(groups={"smokeTest","regressionTest"})
//	public void configBC(String bro) throws Throwable {
//		System.out.println("===Launch the Browser===");
//		String BROWSER = bro;
//				//flib.getDataFromPropertiesFile("bro");
//
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		} else {
//			driver = new ChromeDriver();
//		}
//		String URL = flib.getDataFromPropertiesFile("url");
//		driver.get(URL);
//	}
	
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("===Launch the Browser===");
		String BROWSER = flib.getDataFromPropertiesFile("bro");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		String URL = flib.getDataFromPropertiesFile("url");
		driver.get(URL);
	}

	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println(" Login ");

		String USERNAME = flib.getDataFromPropertiesFile("un");
		String PASSWORD = flib.getDataFromPropertiesFile("pwd");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}
	

	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAM() {
		System.out.println(" Logout ");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups={"smokeTest","regressionTest"})
	public void configAC() throws Throwable {
		System.out.println("=== close the browser ===");
		driver.quit();
	}

	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("===close Db, Report backUP====");
		dblib.closeDbaseconnection();
		
	}

}
