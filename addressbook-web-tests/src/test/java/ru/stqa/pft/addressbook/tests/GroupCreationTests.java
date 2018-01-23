package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
    public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData("test11", null, null); // null - заполнение поля значениями по умолчанию
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

  /*  int max = 0;    //Поиск в цикле максимального идентификатора группы, который будет соответствовать только что добавленной группе
    for (GroupData g: after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    } */
    // after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId() <-- Сравнение объектов по id с помощью анонимной функции (лямбда-выражения). И поиск максимального id группы

   // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); //Установка этого идентификатора
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //Сравнение двух групп по идентификаторам
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
