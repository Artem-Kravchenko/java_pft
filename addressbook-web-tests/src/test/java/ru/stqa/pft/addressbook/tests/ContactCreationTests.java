package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test (enabled = false)
  public void testContactCreation() {
    app.getContactHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoContactPage();
    ContactData contact = new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", "test1");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


  //  contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); //Установка этого идентификатора
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //Сравнение двух контактов по идентификаторам
    before.sort(byId); //Сортировка по id до добавления нового контакта
    after.sort(byId); //Сортировка по id после добавления нового контакта
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
