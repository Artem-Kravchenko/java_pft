package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class JoinContactToGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() { //Создание группы и контакта с группой в предусловии
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    app.contact().gotoHomePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("John").withLastName("Smith").withAddressPrimary("Moscow city")
              .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testJoinContactToGroup() {
    int contactId = 0;
    boolean completed = false;
    Groups beforeJoiningGroups = null;
    Groups beforeWithAddedGroups = null;
    Groups existedGroups = app.db().groups(); //Получение списка всех возможных групп
    Contacts contacts = app.db().contacts(); //Получение списка всех контактов

    for(ContactData modifiedContact:contacts) { //Перебираем все контакты
      beforeJoiningGroups = modifiedContact.getGroups(); //Выбираем список доступных групп для контакта
      GroupData new_Group = app.contact().GetGroupToJoining(existedGroups, modifiedContact); //Ищем к какой группе можно присоединить контакт
      if (new_Group != null) { //Если такая группа есть, то присоединяем её к
        app.contact().joining(modifiedContact, new_Group);  //Присоединение контакта к группе
        contactId = modifiedContact.getId();
        beforeWithAddedGroups = beforeJoiningGroups.withAdded(new_Group);
        completed = true;
        break;
      }
    }

    if (!completed) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test999").withHeader("Test999").withFooter("Test999")); //Создание новой уникальной группы
      Groups extendedGroups = app.db().groups(); //Считывание из БД нового списка с добавленной группой
      GroupData group = extendedGroups.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get();
      ContactData contact = app.db().contacts().iterator().next();
      contactId = contact.getId();
      app.contact().joining(contact, group); //Присоединение контакта к новой группе
      beforeWithAddedGroups = beforeJoiningGroups.withAdded(group);
    }

    Groups groupAfter = app.db().contactById(contactId).getGroups();
  }

}
