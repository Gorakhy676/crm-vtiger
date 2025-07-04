package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(xpath="//a[text()='Opportunities']")
	private WebElement  opportunitiesLink;
	
	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Compaigns")
	private WebElement campaiggnLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement sign;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signoutBtn;

	
	public WebElement getCampaigLink() {
		return campaiggnLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

		public WebElement getOrgLink() {
		return orgLink;
	}
		
		public WebElement getContactLink() {
			return contactLink;
		}
		public WebElement getSign() {
			return sign;
		}
		public WebElement getSignoutBtn() {
			return signoutBtn;
		}
		
		
	
	public void navigateToCampaginPage() {
		
		Actions act= new Actions(driver);
		act.moveToElement(moreLink).perform();
	}
	
	public void logout() {
		Actions act=new Actions(driver);
		act.moveToElement(sign).perform();
		signoutBtn.click();
	}

}
