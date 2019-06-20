package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class ContactsPage extends BasePage {
	
	//contacts header and title visible
	@FindBy(className ="private-header__heading")
	//xpath = "h1/i18n-string[text()='Contacts']
	WebElement ContactsPageHeader;
	
	@FindBy(xpath="//span[text()='Create contact']")
	WebElement createContactBtn;
	
	@FindBy(xpath="//li//span[text()='Create contact']")
	WebElement createContactBtnSecond;
	
	@FindBy(id="uid-ctrl-1")
	WebElement email;
	
	@FindBy(id="uid-ctrl-2")
	WebElement firstName;
	
	@FindBy(id="uid-ctrl-3")
	WebElement lastName;
	
	@FindBy(id="uid-ctrl-5")
	WebElement jobTitle;
	
	public ContactsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getContactsPageTitle(){
		TimeUtil.mediumWait();
		return driver.getTitle();
	}
	
	public String getContactsPageHeader(){
		TimeUtil.mediumWait();
		return ContactsPageHeader.getText();
	}
	
	public boolean verifyContactsPageHeader(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(ContactsPageHeader));
		return ContactsPageHeader.isDisplayed();
	}
	
	public void createNewContact(String emailVal, String firstname, String lastname, String jobtitle ){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(createContactBtn));
		createContactBtn.click();
		
		//TimeUtil.mediumWait();
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.sendKeys(emailVal);
		
		//TimeUtil.mediumWait();
		wait.until(ExpectedConditions.elementToBeClickable(firstName));
		firstName.sendKeys(firstname);
		
		//TimeUtil.mediumWait();
		wait.until(ExpectedConditions.elementToBeClickable(lastName));
		lastName.sendKeys(lastname);
		
		//TimeUtil.mediumWait();
		wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
		jobTitle.sendKeys(jobtitle);
		
		TimeUtil.mediumWait();
		//wait.until(ExpectedConditions.elementToBeClickable(createContactBtnSecond));
		createContactBtnSecond.click();
	}


}