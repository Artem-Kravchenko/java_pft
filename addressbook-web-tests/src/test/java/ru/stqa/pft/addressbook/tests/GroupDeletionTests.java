package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions()  {
    if (app.db().groups().size() == 0) { //Проверка того, существует ли в базе группы
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups(); //Извлечение списка групп из БД до добавления новой группы
    //iterator - позволяет последовательно перебирать элементы множества
    //next - возвращает первый попавшийся элемент множества
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups(); //Извлечение списка групп из БД после добавления новой группы
    assertThat(after, equalTo(before.withOut(deletedGroup)));

  }

}
