package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectElement();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Dary", "Dar", "Sushchikh", "0954985490", "d.sushchikh@gmail.com", "Kiev", null), false);
    app.getContactHelper().updateContactModification();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
