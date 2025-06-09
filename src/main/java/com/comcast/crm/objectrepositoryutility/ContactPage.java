package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

	
public class ContactPage {

	WebDriver driver;
    public ContactPage(WebDriver driver) {
    	this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(className="dvHeaderText")
    private WebElement headerMsg;
    
    public WebElement getHeaderMsg() {
    	return headerMsg;
    }
    
    @FindBy(id="dtlview_Last Name")
    private WebElement lastNameMsg;
    
    public WebElement getLastNameMsg() {
    	return lastNameMsg;
    }

    @FindBy(xpath = "//img[@title='Create Contact...']")
    private WebElement createNewContactBtn;

    @FindBy(name = "lastname")
    private WebElement lastNameEdit;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement submitBtn;

    public WebElement getCreateNewContactBtn() {
        return createNewContactBtn;
    }

    public WebElement getLastNameEdit() {
        return lastNameEdit;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }

    // Action method to create contact
    public void createContact(String lastName) {
        lastNameEdit.sendKeys(lastName);
        //submitBtn.click();
    }
    public void createContactWithOrg(String lastName) {
    	lastNameEdit.sendKeys(lastName);
        submitBtn.click();
    }
    
}
