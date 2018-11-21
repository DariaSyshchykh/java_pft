package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class ApplicationManager {

  private final ContactHelper contactHelper = new ContactHelper();
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;

  public void init() {
    contactHelper.driver = new FirefoxDriver();
    //baseUrl = "https://www.katalon.com/";
    contactHelper.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    contactHelper.driver.get("http://localhost/addressbook/group.php");
    contactHelper.groupHelper = new GroupHelper(contactHelper.driver);
    navigationHelper = new NavigationHelper(contactHelper.driver);
    sessionHelper = new SessionHelper(contactHelper.driver);
    sessionHelper.login("admin", "secret");
  }

  private void login(String username, String password) {
    contactHelper.driver.findElement(By.name("user")).clear();
    contactHelper.driver.findElement(By.name("user")).sendKeys(username);
    contactHelper.driver.findElement(By.id("LoginForm")).click();
    contactHelper.driver.findElement(By.name("pass")).click();
    contactHelper.driver.findElement(By.name("pass")).clear();
    contactHelper.driver.findElement(By.name("pass")).sendKeys(password);
    contactHelper.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

  public void gotoContactPage() {
    contactHelper.driver.findElement(By.linkText("add new")).click();
  }


  public void stop() {
    contactHelper.driver.quit();
    String verificationErrorString = contactHelper.groupHelper.verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


  private boolean isElementPresent(By by) {
    try {
      contactHelper.driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      contactHelper.driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void chooseElement() {
    contactHelper.driver.findElement(By.id("16")).click();
  }

  public GroupHelper getGroupHelper() {
    return contactHelper.groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
