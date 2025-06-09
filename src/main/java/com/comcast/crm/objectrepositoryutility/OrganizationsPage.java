package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
		
	}
	
	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	public WebElement getSearchDD() {
		return searchDD;
	}
	
	public WebElement getSearchEdit() {
		return searchEdit;
	}
	
	@FindBy(name="submit")
	private WebElement searchbtn;
	
	public WebElement getSearchBtn() {
		return searchbtn;
	}
	
	

}
