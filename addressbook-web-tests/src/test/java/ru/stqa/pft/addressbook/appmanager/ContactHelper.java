package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends BaseHepler {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData, boolean creation ) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddressPrimary());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void gotoContactPage() {
    click(By.linkText("add new"));
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" +id + "']")).click();
  }

  private void SelectedGroupById(String id) {
    new Select(wd.findElement(By.name("group"))).selectByValue(id);}


  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAcontact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void create(ContactData contact, boolean withSelectedGroup) {
    gotoContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    gotoHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmContactDeletion();
    gotoHomePage();
  }

  public void joining(ContactData contact, GroupData group) { //Присоединение контакта к группе
    gotoHomePage();
    selectContactById(contact.getId());
    joinSelectedContactToGroup(group);
    gotoHomePage();
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) { //Удаление контакта из группы
    gotoHomePage();
    SelectedGroupById(String.valueOf(group.getId()));
    selectContactById(contact.getId());
    click(By.name("remove"));
    gotoHomePage();
    SelectedGroupById("");
    gotoHomePage();
  }


  public void joinSelectedContactToGroup(GroupData group) { //Выбор группы для контакта из списка
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
    click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
  }


  public GroupData GetGroupToJoining(Groups groups, ContactData contact){ //Получение списка групп, к котором может быть присоединён контакт
    Groups beforeJoiningGroups = contact.getGroups();
    for (GroupData group :groups) {
      if (!beforeJoiningGroups.contains(group)) {
        return group;
      }
    }
    return null;
  }


  public Contacts all() { // Функция для работы с множеством элементов
    Contacts contacts = new Contacts(); //Создаём объект для работы с множеством элементов
    List<WebElement> rows = wd.findElements(By.name("entry")); //Находим все элементы типа "Контакты" на странице (По записям)
    for (WebElement row :  rows) { //В цикле перебираем все элементы полученного списка
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждого контакта
      String lastname = cells.get(1).getText(); // Считывание фамилии
      String firstname = cells.get(2).getText(); //Считывание имени
      String allPhones = cells.get(5).getText(); //Разрезание строки на несколько частей и записыванием результата в переменную allPhones
      String allEmails = cells.get(4).getText();
      String addressPrimary = cells.get(3).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAddressPrimary(addressPrimary)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones)); //Добавляем объект (Каждый считанный контакт) в список
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {  //Функция считывания данных из формы редактирования контакта
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String addressPrimary = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddressPrimary(addressPrimary)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

 }
