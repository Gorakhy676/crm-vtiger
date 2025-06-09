package com.comcast.crm.orgtest;
/**
author Gorakh Yadav
organisation page 
**/

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listnerutility.ListnerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass {

    @Test(groups = "smokeTest")
    public void createOrganizationTest() throws Throwable {
        String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
        Reporter.log("Creating Organization: " + orgName, true);

        HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();

        OrganizationsPage op = new OrganizationsPage(driver);
        op.getCreateNewOrgBtn().click();

        CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
        cnop.createOrg(orgName);
        cnop.getSavebtn().click();

        OrganizationInfoPage oip = new OrganizationInfoPage(driver);
        String actOrgName = oip.getHeaderVerify().getText();

        Assert.assertTrue(actOrgName.contains(orgName), "Organization name verification failed");
        Reporter.log("Verified organization: " + actOrgName, true);
    }

    @Test(groups = "regressionTest")
    public void createOrganizationWithIndustry() throws Throwable {
        String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
        String industry = elib.getDataFromExcel("org", 4, 3);
        String type = elib.getDataFromExcel("org", 4, 4);

        HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();

        OrganizationsPage op = new OrganizationsPage(driver);
        op.getCreateNewOrgBtn().click();

        CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
        cnop.createOrg(orgName);
        
        Select selIndustry = new Select(driver.findElement(By.name("industry")));
        selIndustry.selectByVisibleText(industry);

        Select selType = new Select(driver.findElement(By.name("accounttype")));
        selType.selectByVisibleText(type);

        cnop.getSavebtn().click();

        String actIndustries = driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font")).getText();
        Assert.assertEquals(actIndustries, industry, "Industry verification failed");

        String actType = driver.findElement(By.id("dtlview_Type")).getText();
        Assert.assertEquals(actType, type, "Type verification failed");

        Reporter.log("Verified Industry: " + industry + " and Type: " + type, true);
    }

    @Test(groups = "regressionTest")
    public void createOrgWithPhoneNumber() throws Throwable {
        String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
        String phoneNumber = elib.getDataFromExcel("org", 7, 3) + jlib.getRandomNumber();

        HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();

        OrganizationsPage op = new OrganizationsPage(driver);
        op.getCreateNewOrgBtn().click();

        CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
        cnop.createOrg(orgName);
        driver.findElement(By.id("phone")).sendKeys(phoneNumber);

        cnop.getSavebtn().click();

        OrganizationInfoPage oip = new OrganizationInfoPage(driver);
        String actOrgName = oip.getHeaderVerify().getText();
        Assert.assertTrue(actOrgName.contains(orgName), "Org name verification failed");

        String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
        Assert.assertTrue(actPhoneNumber.contains(phoneNumber), "Phone number verification failed");

        Reporter.log("Verified Phone Number: " + phoneNumber, true);
    }
}
