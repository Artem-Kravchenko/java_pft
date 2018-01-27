package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test (enabled = false)
  public void testContactModification (){
    app.getContactHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getContactHelper().createContact(new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
   // app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Jimmy", "Smith", "New York City", "89001234567", "email@email.com", null);
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //Сравнение двух контактов по идентификаторам
    before.sort(byId); //Сортировка по id до добавления нового контакта
    after.sort(byId); //Сортировка по id после добавления новой контакта
    Assert.assertEquals(before, after);
    //Assert.assertEquals(before, after);
  }
}
