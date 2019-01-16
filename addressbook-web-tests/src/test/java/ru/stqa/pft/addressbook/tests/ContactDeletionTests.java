package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests  extends TestBase{

  @Test
  public void testDeletionContact() throws Exception {
    Contacts before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().gotoHomePage();
    ContactData contact = new ContactData().withId(before.size() - 1);
    app.getContactHelper().selectContact(deletedContact.getId());
    app.getContactHelper().submitSelection();
    app.getContactHelper().deleteElement();
    app.getContactHelper().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.getContactHelper().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}

