package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getContactHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getContactHelper().createContact(new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmContactDeletion();
    app.getContactHelper().returnToHomePage();
  }
}
