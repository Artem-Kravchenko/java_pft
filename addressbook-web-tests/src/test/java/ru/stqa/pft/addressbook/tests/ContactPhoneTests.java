package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase{

  @Test
  public void testContactPhone(){
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) { //Фильтрация и склеивание полученных строк (Метод обратных проверок для телефонов)
   return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleaned)
           .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) { //Фильтрация и склеивание полученных строк (Метод обратных проверок для email'ов)
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }




  public static String cleaned(String phone){ //Функция очистки строки от пробелов и замены некоторых символов на пустые строки
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
