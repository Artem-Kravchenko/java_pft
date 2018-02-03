package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.contact().gotoHomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/tiger.jpg"); //Создаём объект типа "Файл" с указанием относительного пути, где он лежит
    ContactData contact = new ContactData().withFirstName("John").withLastName("Smith").withPhoto(photo);
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  /*
  @Test //Тест для вывода относительного и абсолютного путей к файлу, проверки того, что файл существует
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/tiger.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
  */
}
