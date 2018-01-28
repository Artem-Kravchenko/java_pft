package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.getContactHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getContactHelper().createContact(new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", null));
    }
  }

  @Test
  public void testContactModification (){
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(),"Jimmy", "Smith", "New York City", "89001234567", "email@email.com", null);
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //Сравнение двух контактов по идентификаторам
    before.sort(byId); //Сортировка по id до добавления нового контакта
    after.sort(byId); //Сортировка по id после добавления новой контакта
    Assert.assertEquals(before, after);
   }


}
