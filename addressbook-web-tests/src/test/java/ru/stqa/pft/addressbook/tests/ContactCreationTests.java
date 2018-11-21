package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Dary", "Dar", "Sushchikh", "0954985490", "d.sushchikh@gmail.com", "Kiev"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }

}
