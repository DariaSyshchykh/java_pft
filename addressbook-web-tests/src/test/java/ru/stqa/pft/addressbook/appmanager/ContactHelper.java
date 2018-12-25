package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
   /* type((By.name("mobile")), contactData.getMobile());
    type((By.name("email")), contactData.getEmail());
    type((By.name("address2")),contactData.getAddress());

    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse (isElementPresent (By.name("new_group")));
    }*/
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

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();;
  }

  public void deleteElement() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public void gotoHomePage() {
    driver.findElement(By.linkText("home")).click();
  }

 /* public void initContactModification() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='import'])[1]/following::img[5]")).click();

  }
  */
  public void initContactModification(int id) {
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }


  public void updateContactModification() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
    elements.remove(0);
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, name, null, lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}
