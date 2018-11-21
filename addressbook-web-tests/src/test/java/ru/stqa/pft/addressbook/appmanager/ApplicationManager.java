package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class ApplicationManager {
  private WebDriver driver;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    driver = new FirefoxDriver();
    //baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login("admin", "secret");
  }

  private void login(String username, String password) {
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.id("LoginForm")).click();
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

  public void returnToContactPage() {
    driver.findElement(By.linkText("home page")).click();
  }

  public void submitContactCreation() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).clear();
    driver.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("address2")).click();
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys(contactData.getAddress());
  }

  public void gotoContactPage() {
    driver.findElement(By.linkText("add new")).click();
  }


  public void stop() {
    driver.quit();
    String verificationErrorString = groupHelper.verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (groupHelper.acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      groupHelper.acceptNextAlert = true;
    }
  }

  protected void submitDeletionContact() {
    boolean acceptNextAlert = true;
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void chooseElement() {
    driver.findElement(By.id("16")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
  public void submitSelection() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
  }

  public void selectElement() {
    driver.findElement(By.id("17")).click();
    boolean acceptNextAlert = true;
  }

  public void deleteElement() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void gotoHomePage() {
    driver.findElement(By.linkText("home")).click();
  }
}
