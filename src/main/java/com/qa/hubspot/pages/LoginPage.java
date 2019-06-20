package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	WebDriver driver;

	//page objects with page factory
	@FindBy(id="username")
	WebElement emailId;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="loginBtn")
	WebElement loginButton;
	
	@FindBy(linkText = "Sign up")
	WebElement signUpLink;
	
	//create the constructor of this class
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	} 
	
	//define page action/methods
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	
	//verify signup link
	public boolean verifySignUpLink(){
	   return signUpLink.isDisplayed();	
	}
	
	public HomePage doLogin(String username, String pass){
		{
			emailId.sendKeys(username);
			password.sendKeys(pass);
			loginButton.click();
			
			return new HomePage(driver);
		}
	}
}
