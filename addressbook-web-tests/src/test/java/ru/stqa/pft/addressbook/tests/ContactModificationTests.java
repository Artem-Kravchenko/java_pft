package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification (){
    app.getContactHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getContactHelper().createContact(new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", null));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Bill", "Smith", "New York City", "89001234567", "email@email.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
