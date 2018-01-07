package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
   public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().getBaseHepler().selectGroup();
    app.getGroupHelper().getBaseHepler().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test0", "test0", "test0"));
    app.getGroupHelper().getBaseHepler().submitGroupModification();
    app.getGroupHelper().getBaseHepler().returnToGroupPage();
  }
}
