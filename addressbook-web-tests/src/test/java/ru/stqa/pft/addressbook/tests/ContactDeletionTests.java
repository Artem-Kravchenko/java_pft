package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("John").withLastName("Smith")
              .withAddress("New York City").withMobilePhoneNumber("89001234567").withEmail("email@email.com"));
    }
  }

  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next(); //Выбор первого попавшегося элемента множества
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact); //Удаляем из старого множества элемент, чтобы получить два одинаковых множества
    Assert.assertEquals(before, after); // Сравниваем два множества
  }

}

