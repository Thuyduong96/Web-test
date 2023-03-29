package com.automation.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PropertiesFileUtils;
public class LoginPage {
	WebDriver driver;
	Actions act;
	PropertiesFileUtils Pro = new PropertiesFileUtils();
	
	WebElement 	Click_icon_signin,
				inputEmail,
				inputPassword,
				btnLogin;
	public LoginPage(WebDriver driver) {
		act = new Actions(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void icon_signin()throws Exception {
		Thread.sleep(2000);
		Click_icon_signin = driver.findElement(By.xpath(Pro.getProperty("icon_signin_")));
		act.click(Click_icon_signin).build().perform();
		Thread.sleep(2000);
	}
	
	public void enterEmail(String email){
		inputEmail = driver.findElement(By.xpath(Pro.getProperty("login_email_")));
		act.sendKeys(inputEmail,email).build().perform();
		}
	
	public void enterPassword(String password){
		inputPassword = driver.findElement(By.name(Pro.getProperty("login_pasword_")));
		act.sendKeys(inputPassword,password).build().perform();
		}
	
	public void clickSignIn() throws Exception {
		btnLogin = driver.findElement(By.xpath(Pro.getProperty("login_signin_")));
		act.click(btnLogin).build().perform();
		Thread.sleep(2000);
	}
}
