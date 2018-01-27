package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions (){
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAgroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1; //Переменная для выбора конкретной группы по индексу
    GroupData group = new GroupData(before.get(index).getId(), "test1", "test0", "test0");
    app.getGroupHelper().modifyGroup(index, group); //Выбор и модификация конкретной группы (По индексу)
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //Сравнение двух групп по идентификаторам
    before.sort(byId); //Сортировка по id до добавления новой группы
    after.sort(byId); //Сортировка по id после добавления новой группы
    Assert.assertEquals(before, after);

    // before.get(before.size() - 1).getId() - сохранение и указание старого идентификатора при сравнении двух множеств
    // до модифицировакации группы и после

  }


}
