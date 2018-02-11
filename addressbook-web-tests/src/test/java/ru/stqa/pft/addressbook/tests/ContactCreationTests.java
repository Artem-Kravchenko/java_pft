package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider //Провайдер тестовых данных для формата .xml
  public Iterator<Object[]> validContactsFromXml() throws IOException { //Список массивов объектов
   try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) { //Чтение данных для теста из файла
     String xml = "";
     String line = reader.readLine(); // Чтение строки из файла
     while (line != null) {
       xml += line;
       line = reader.readLine();
     }
     XStream xStream = new XStream();
     xStream.processAnnotations(ContactData.class);
     List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
     return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
   }
  }

  @DataProvider //Провайдер тестовых данных для формата .json
  public Iterator<Object[]> validContactsFromJson() throws IOException { //Список массивов объектов
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) { //Чтение данных для теста из файла
      String json = "";
      String line = reader.readLine(); // Чтение строки из файла
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.contact().gotoHomePage();
    app.contact().create(contact, true);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
