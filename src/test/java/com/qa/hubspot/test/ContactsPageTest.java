package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;
import com.qa.hubspot.util.TimeUtil;

public class ContactsPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setup(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		TimeUtil.mediumWait();
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		TimeUtil.mediumWait();
		contactsPage = homePage.goToContactsPage();
		
		//TimeUtil.mediumWait();
	}
	
	@Test(priority=1, enabled=false)
	public void verifyContactsPageTitleTest(){
		String title = contactsPage.getContactsPageTitle();
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
		
	}
	
	@Test(priority=2, enabled=false)
	public void verifyContactsPageHeaderTest(){
		String headerName = contactsPage.getContactsPageHeader();
		Assert.assertEquals(headerName, Constants.CONTACTS_PAGE_HEADER);
	}
	
	@Test(priority=3, enabled=false)
	public void verifyContactsPageHeader(){
		Assert.assertTrue(contactsPage.verifyContactsPageHeader());
	}
	
	@Test(priority=4, dataProvider="getContactsData")
	public void createNewContactsTest(String email, String firstName, String lastName, String jobTitle){
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}
	
	@DataProvider(name = "getContactsData")
	public Object[][] getContactTestData(){
		Object contactsData[][] = ExcelUtil.getTestData(Constants.CONTACT_SHEET_NAME);
		return contactsData;
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
