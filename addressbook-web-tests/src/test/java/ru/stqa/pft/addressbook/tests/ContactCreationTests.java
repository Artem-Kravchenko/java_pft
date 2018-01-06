package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().getBaseHepler().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com"));
    app.getContactHelper().getBaseHepler().submitContactCreation();
    app.getContactHelper().getBaseHepler().returnToHomePage();
  }
}
