package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.getContactHelper().all();
    ContactData modifiedContact = before.iterator().next();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact(modifiedContact.getId());
    ContactData contact =  new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Dary").withMiddlename("Dar").withLastname("Sushchikh");
    app.getContactHelper().initContactModification(contact.getId());
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContactModification();
    app.getContactHelper().gotoHomePage();
    Set<ContactData> after = app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
