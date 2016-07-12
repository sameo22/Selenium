package com.selenium.google.tests;

/**
 * @author Santiago Mejia Osorio
 * @date 12/07/2016
 */

import com.selenium.google.common.Browser;
import com.selenium.google.pages.Login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest  {

    public WebDriver driver;

    /**
     * The annotated method will be run before all tests in this suite have run.
     */  
    @BeforeSuite
    public void setUp(){
        this.driver = Browser.getDriver();
    }

    /**
     * The annotated method will be run after all tests in this suite have run.
     */  
    @AfterSuite
    public void tearDown(){
        driver.close();
    }

	@Test
	public void blankUserNameRightPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "rightpassword");
		Assert.assertTrue(login.getErrorMsg().contains("Enter your email address."));
	}

	@Test
	public void wrongUserNameRightPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform(" wrongUserName", "rightpassword");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Enter your email address."));
	}

	@Test
	public void blankUserNameWrongPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "wrongpassword");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Username cannot be empty"));
	}
	@Test
	public void blankUserNameBlankPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Username and password cannot be empty"));
	}
}
