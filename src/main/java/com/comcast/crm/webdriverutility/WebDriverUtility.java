package com.comcast.crm.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
 
	public void switchToTabOnURL(WebDriver driver,String partialURL) {
		 Set<String> allWindows = driver.getWindowHandles();
	        Iterator<String> it = allWindows.iterator();
	        
	        while (it.hasNext()) {
	            String windowID = it.next();
	            driver.switchTo().window(windowID);
	            
	            String acturl=driver.getCurrentUrl();
	            if (driver.getCurrentUrl().contains("partialURL")) {
	                break;
	            }
	        }
	}
	
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		 Set<String> allWindows = driver.getWindowHandles();
	        Iterator<String> it = allWindows.iterator();
	        
	        while (it.hasNext()) {
	            String windowID = it.next();
	            driver.switchTo().window(windowID);
	            
	            String acturl=driver.getCurrentUrl();
	            if (driver.getCurrentUrl().contains("partialTitle")) {
	                break;
	            }
	        }
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	} 
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancle(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void mousemoveonElement(WebDriver driver,WebElement element)
	{  
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();;
		
	}
	public void doubleclick(WebDriver driver,WebElement element)
	{ 
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
		
	}
	
	
	 // 1. Wait for Element to be Visible
    public static void waitForElementVisible(WebDriver driver, WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            System.out.println("Element not visible within " + timeInSeconds + " seconds.");
        }
    }

    // 2. Wait for Element to be Clickable
    public static void waitForElementClickable(WebDriver driver, WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            System.out.println("Element not clickable within " + timeInSeconds + " seconds.");
        }
    }

    // 3. Wait for Element Presence using By locator
    public static void waitForPresenceOfElement(WebDriver driver, By locator, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not present within " + timeInSeconds + " seconds.");
        }
    }

    // 4. Wait for Alert
    public static void waitForAlert(WebDriver driver, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("Alert not found within " + timeInSeconds + " seconds.");
        }
    }

    // 5. Wait for Title
    public static void waitForTitleContains(WebDriver driver, String titleSubstring, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.titleContains(titleSubstring));
        } catch (TimeoutException e) {
            System.out.println("Title did not contain expected text within " + timeInSeconds + " seconds.");
        }
    }

    // 6. FluentWait Example
    public static void waitWithFluentWait(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        wait.until(driver1 -> element.isDisplayed());
    }
	
}









