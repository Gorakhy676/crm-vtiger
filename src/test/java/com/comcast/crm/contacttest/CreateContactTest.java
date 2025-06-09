package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;

public class CreateContactTest extends BaseClass {
	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {
		
			
		//read testScript data from excel file
		String lastName=elib.getDataFromExcel("contact", 1, 2) +jlib.getRandomNumber();
		
	// step2 : navigate to contact module
		HomePage hp=new HomePage(driver);
		  hp.getContactLink().click();
		  Thread.sleep(20);
		
	// step 3: click create contact button	  
		  ContactPage cp = new ContactPage(driver);
	        cp.getCreateNewContactBtn().click();
	        cp.createContactWithOrg(lastName);
		  
		 
		  
		  //verify Header msgExpected Result
	        
	        String actHeader=cp.getHeaderMsg().getText();
	       boolean status1= actHeader.contains(lastName);
	        Assert.assertEquals(status1, true);
	        
		String actLastName=  cp.getLastNameMsg().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertAll();
		assertobj.assertEquals(actLastName, lastName);
		  
		  
	


	}

}
