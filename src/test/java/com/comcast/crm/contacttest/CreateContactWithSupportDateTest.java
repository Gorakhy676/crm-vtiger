package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
		/*FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		*/
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		String BROWSER = flib.getDataFromPropertiesFile("bro");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("un");
		String PASSWORD = flib.getDataFromPropertiesFile("pwd");
		
		//generate the random number
	/*	Random random=new Random();
		int randomInt=random.nextInt(1000);*/
		
		
		//read testScript data from excel file
		FileInputStream fis1 = new FileInputStream("./testdata/contact.xls");
		Workbook wb=  WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("contact");
		Row row= sheet.getRow(4);
		
		String lastName=row.getCell(2).toString() + jlib.getRandomNumber();
		  System.out.println(lastName);
		  wb.close();
		  
		  
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}

		
		  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		  driver.get(URL);
		  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		  driver.findElement(By.id("submitButton")).click(); 
		  driver.findElement(By.linkText("Contacts")).click();
		 
		  driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		  
		/*  Date dateObj=new Date();
		  SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
		  String startDate=sim.format(dateObj);
		  System.out.println(startDate);
		  
		  Calendar cal=sim.getCalendar();
		  cal.add(Calendar.DAY_OF_MONTH, 30);
		  String endDate=sim.format(cal.getTime());
		  System.out.println(endDate);*/
		String startDate = jlib.getSystemDateYYYYDDMM();
		 String endDate= jlib.getRequireDateYYYYDDMM(30);
		  
		  driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		  driver.findElement(By.name("support_start_date")).clear();
		  driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		  
		  driver.findElement(By.name("support_end_date")).clear();
		  driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		 
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //verify Header msgExpected Result
		String actLastName=  driver.findElement(By.id("dtlview_Last Name")).getText();
		  if(actLastName.contains(lastName)) {
			  System.out.println(lastName+"is verified");
		  }
		  else {
			  System.out.println(lastName+"is verified");
		  }
		
		  String actSatrtDate=driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		  if(actSatrtDate.equals(startDate)) {
			  System.out.println(startDate+" is verified");
		  }
		  else {
			  System.out.println(startDate+" is not verified");
		  }
		  
		  String actEndDate=driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		  if(actEndDate.equals(endDate)) {
			  System.out.println(endDate+" is verified");
		  }
		  else {
			  System.out.println(endDate+" is not verified");
		  }
		  
		  
		driver.quit();


	}

}
