package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

    Groups groups = app.db().groups();
    app.contact().gotoHomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("John").withLastName("Smith")
              .withAddressPrimary("Moscow sity").inGroup(groups.iterator().next()), true);
    } else {
      ContactData contact = app.db().contacts().iterator().next();
      if (contact.getGroups().size() == 0) {
        contact.inGroup(groups.iterator().next());
      }

    }
  }

  @Test
  public void testRemoveContactFromGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups before = contact.getGroups();
    GroupData removedGroup = before.iterator().next();
    app.contact().removeContactFromGroup(contact, removedGroup);
    Groups after = app.db().contactById(contact.getId()).getGroups();
    assertThat(after, equalTo(before.withOut(removedGroup)));
  }
}
