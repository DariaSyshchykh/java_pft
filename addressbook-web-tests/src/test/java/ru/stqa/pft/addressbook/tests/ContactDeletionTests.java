package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests  extends TestBase{

  @Test (enabled = false)
  public void testDeletionContact() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().submitSelection();
    app.getContactHelper().deleteElement();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

