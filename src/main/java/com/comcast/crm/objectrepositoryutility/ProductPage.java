package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement outside constructor
    @FindBy(xpath = "//a[text()='Products']")
    private WebElement productLink;

    // Getter method
    public WebElement getProductLink() {
        return productLink;
    }

    // Business logic method
    public void clickOnProductLink() {
        productLink.click();
    }
}
