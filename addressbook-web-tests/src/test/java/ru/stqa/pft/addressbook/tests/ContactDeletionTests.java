package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests  extends TestBase{

  @Test
  public void testDeletionContact() throws Exception {
    Set<ContactData> before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().gotoHomePage();
    ContactData contact = new ContactData().withId(before.size() - 1);
    app.getContactHelper().selectContact(deletedContact.getId());
    app.getContactHelper().submitSelection();
    app.getContactHelper().deleteElement();
    app.getContactHelper().gotoHomePage();
    Set<ContactData> after = app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}

