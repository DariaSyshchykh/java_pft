package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper  extends HelperBase{

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

  public void submitSelection() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
    contactCache = null;

  }

  public void selectContact(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();;
    contactCache = null;
  }

  public void deleteElement() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    contactCache = null;
  }

  public void gotoHomePage() {
    driver.findElement(By.linkText("home")).click();
    contactCache = null;
  }

  public void initContactModification(int id) {
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    contactCache = null;
  }


  public void updateContactModification() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
    contactCache = null;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return  new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
    elements.remove(0);
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }
}
