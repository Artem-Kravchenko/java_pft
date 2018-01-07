package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getContactHelper().getBaseHepler().selectContact();
    app.getContactHelper().getBaseHepler().deleteContact();
    app.getContactHelper().getBaseHepler().confirmContactDeletion();
    app.getContactHelper().getBaseHepler().returnToHomePage();
  }
}
