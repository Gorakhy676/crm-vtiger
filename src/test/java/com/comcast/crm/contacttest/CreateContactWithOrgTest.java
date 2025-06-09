package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrgTest extends BaseClass {

    @Test(groups = "regressionTest")
    public void createContactWithOrgTest() throws Throwable {

        // Step 1: Read test data from Excel
        String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
        String contactLastName = elib.getDataFromExcel("contact", 7, 3);

        // Step 2: Wait for the page to load completely
        wlib.waitForPageToLoad(driver);

        // Step 3: Navigate to Organizations and create a new organization
        HomePage home = new HomePage(driver);
        home.getOrgLink().click();

        OrganizationsPage orgPage = new OrganizationsPage(driver);
        orgPage.getCreateNewOrgBtn().click();

        CreateNewOrganizationPage createOrg = new CreateNewOrganizationPage(driver);
        createOrg.createOrg(orgName);
        createOrg.getSavebtn().click();

        // Step 4: Validate organization creation
        String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if (orgHeader.contains(orgName)) {
            System.out.println(" Organization created: " + orgName);
        } else {
            System.out.println(" Organization creation failed: " + orgName);
        }

        // Step 5: Navigate to Contacts module and create a new contact
        home.getContactLink().click();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.getCreateNewContactBtn().click();
        contactPage.createContact(contactLastName);

        // Step 6: Select organization for the contact
        driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

        // Step 7: Switch to child window and select the newly created organization
        wlib.switchToTabOnURL(driver, "module=Accounts");
        driver.findElement(By.name("search_text")).sendKeys(orgName);
        driver.findElement(By.name("search")).click();
        driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

        // Step 8: Switch back to parent window
        wlib.switchToTabOnTitle(driver, "Contacts&action");

        // Step 9: Save the contact
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        // Step 10: Validate contact creation
        String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if (contactHeader.contains(contactLastName)) {
            System.out.println(" Contact created: " + contactLastName);
        } else {
            System.out.println("Contact creation failed: " + contactLastName);
        }

        // Step 11: Validate organization linkage
        String linkedOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();
        if (linkedOrgName.contains(orgName)) {
            System.out.println(" Contact is linked to organization: " + orgName);
        } else {
            System.out.println(" Contact is NOT linked to organization: " + orgName);
        }
    }
}
