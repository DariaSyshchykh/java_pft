package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact(before.size() - 1);
    ContactData contact =  new ContactData(before.get(before.size()-1).getId(),"Dary", "Dar", "Sushchikh");
    app.getContactHelper().initContactModification(contact.getId());
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContactModification();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
