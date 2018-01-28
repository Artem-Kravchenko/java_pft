package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.contact().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("John").withLastName("Smith")
            .withAddress("New York City").withMobilePhoneNumber("89001234567").withEmail("email@email.com")
            .withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

   contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()); //Поиск и присвоение максимального идентификатора
   before.add(contact);
   Assert.assertEquals(before, after);
   }
}
