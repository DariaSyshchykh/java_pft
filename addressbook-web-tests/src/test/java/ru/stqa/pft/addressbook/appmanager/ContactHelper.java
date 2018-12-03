package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactHelper  extends HelperBase{
  //private WebDriver driver;
 // protected GroupHelper groupHelper;

  public ContactHelper(WebDriver driver) {
    super(driver);
  }


  public void returnToContactPage() {
    driver.findElement(By.linkText("home page")).click();
  }

  public void submitContactCreation() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    type((By.name("firstname")), contactData.getFirstname());
    type((By.name("middlename")),contactData.getMiddlename());
    type((By.name("lastname")), contactData.getLastname());
    type((By.name("mobile")), contactData.getMobile());
    type((By.name("email")), contactData.getEmail());
    type((By.name("address2")),contactData.getAddress());
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  protected void submitDeletionContact() {
    boolean acceptNextAlert = true;
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void submitSelection() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
  }

  public void selectElement() {
    driver.findElement(By.id("30")).click();
    boolean acceptNextAlert = true;
  }

  public void deleteElement() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void gotoHomePage() {
    driver.findElement(By.linkText("home")).click();
  }
}
