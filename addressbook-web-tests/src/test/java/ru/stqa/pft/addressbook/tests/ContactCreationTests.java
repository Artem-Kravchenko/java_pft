package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.contact().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("John", "Smith", "New York City", "89001234567", "email@email.com", "test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //Сравнение двух контактов по идентификаторам
    before.sort(byId); //Сортировка по id до добавления нового контакта
    after.sort(byId); //Сортировка по id после добавления нового контакта
    Assert.assertEquals(before, after);
   }
}
