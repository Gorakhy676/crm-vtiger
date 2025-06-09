package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.webdriverutility.WebDriverUtility;
/**
*author Gorakh Yadav
*Contains Login page element & business lib like login 
**/

public class LoginPage extends WebDriverUtility {
	
	// Rule-2 Object Creation
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//input[@name='user_name']")
	private WebElement usernameEdit;
	
	@FindBy(xpath="//input[@name='user_password']")
	private WebElement passwordEdit; 
	
	@FindBy(xpath="//input[@id='submitButton']")
	private WebElement loginEdit;
	
	//Rule-3 Object Initilization
	//Rule-3 Object Encapsulation

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginEdit() {
		return loginEdit;
	}
	/**
	 * login to application based on username,password argument
	 * @param username
	 * @param password
	 */
	//Rule-5 Provide Action
	public void loginToApp(String username,String password) {
		waitForPageToLoad(driver);
		usernameEdit.sendKeys("admin");
		passwordEdit.sendKeys("admin");
		loginEdit.click();
		
	}
	
	
	
	
	
	
	
	
	
	

}
