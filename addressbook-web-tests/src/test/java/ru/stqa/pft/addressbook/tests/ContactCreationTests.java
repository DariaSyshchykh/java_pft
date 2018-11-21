package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoContactPage();
    app.fillContactForm(new ContactData("Dary", "Dar", "Sushchikh", "0954985490", "d.sushchikh@gmail.com", "Kiev"));
    app.submitContactCreation();
    app.returnToContactPage();
  }

}
