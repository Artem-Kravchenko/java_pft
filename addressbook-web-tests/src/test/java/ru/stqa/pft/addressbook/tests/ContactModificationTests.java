package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification (){
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Bill", "Smith", "New York City", "89001234567", "email@email.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().getBaseHepler().returnToHomePage();
  }
}
