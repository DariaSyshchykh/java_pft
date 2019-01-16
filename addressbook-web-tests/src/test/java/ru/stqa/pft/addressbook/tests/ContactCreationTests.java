package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.getContactHelper().all();
    app.goTo().gotoContactPage();
    ContactData contact = new ContactData().withFirstname("Dary1").withMiddlename("Dar").withLastname("Sushchikh");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    Contacts after = app.getContactHelper().all();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() throws Exception {
    Contacts before = app.getContactHelper().all();
    app.goTo().gotoContactPage();
    ContactData contact = new ContactData().withFirstname("Dary1'").withMiddlename("Dar'").withLastname("Sushchikh'");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.getContactHelper().all();
    assertThat(after, equalTo(before));
  }

}