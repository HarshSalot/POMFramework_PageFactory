package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

public class HomePageTest {
	

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		TimeUtil.mediumWait();
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//TimeUtil.mediumWait();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle(){
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeader(){
		String headerName = homePage.getHomePageHeader();
		Assert.assertEquals(headerName, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserAccountName(){
		String accountName=homePage.getLoggedInAccountValue();
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
