package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests  extends TestBase{

  @Test
  public void testDeletionContact() throws Exception {
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectElement();
    app.getContactHelper().submitSelection();
    app.getContactHelper().deleteElement();
    app.getContactHelper().gotoHomePage();
  }
}
