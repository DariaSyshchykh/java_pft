package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class ApplicationManager {

  protected WebDriver driver;

  protected GroupHelper groupHelper;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      driver = new InternetExplorerDriver();
    }
    //baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/");
    contactHelper = new ContactHelper(driver);
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper goTo
          () {
    return navigationHelper;
  }

}

