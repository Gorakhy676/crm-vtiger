package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	public WebDriver driver;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgNameEdit;
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	@FindBy(id = "phone")
	private WebElement phoneField;
	public WebElement getPhoneField() {
		return phoneField;
	}
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	public WebElement getIndustryDD() {
		return industryDD;
	}
	
	@FindBy(xpath="//input[@title='Save [Alt+S]' ]")
	private WebElement savebtn;
	
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);
	}
	
	public void createOrg(String orgName,String industry) {
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(industryDD);
		sel.selectByVisibleText(industry);
		savebtn.click();
		
	}


	public void createOrgPhoneNumber(String orgName, String phoneNumber) {
		orgNameEdit.sendKeys(orgName);
		phoneField.sendKeys(phoneNumber);
		savebtn.click();
	}


	
	

}
