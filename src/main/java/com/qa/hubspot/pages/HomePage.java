package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class HomePage extends BasePage {

	@FindBy(xpath="//h1[@class='private-page__title']")
	WebElement homePageHeader;
	
	@FindBy(xpath="//span[@class='account-name ']")
	WebElement accountName;
	
	@FindBy(id ="nav-primary-contacts-branch")
	WebElement ContactsHoverMenu;
	@FindBy(id = "nav-secondary-contacts")
	WebElement ContactsPage;
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE));
		return driver.getTitle();
	}
	
	public String getHomePageHeader(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
		return homePageHeader.getText();
	}
	
	public String getLoggedInAccountValue(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(accountName));
		return accountName.getText();
	}
	
	public ContactsPage goToContactsPage(){
		ContactsHoverMenu.click();
		TimeUtil.shortWait();
		ContactsPage.click();
		return new ContactsPage(driver);
		
	}
}
