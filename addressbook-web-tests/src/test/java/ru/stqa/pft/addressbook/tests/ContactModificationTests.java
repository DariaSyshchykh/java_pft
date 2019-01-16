package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    Contacts before = app.getContactHelper().all();
    ContactData modifiedContact = before.iterator().next();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact(modifiedContact.getId());
    ContactData contact =  new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Dary").withMiddlename("Dar").withLastname("Sushchikh");
    app.getContactHelper().initContactModification(contact.getId());
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContactModification();
    app.getContactHelper().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.getContactHelper().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
