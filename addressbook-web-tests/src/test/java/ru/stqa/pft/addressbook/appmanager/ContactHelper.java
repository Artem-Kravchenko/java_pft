package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    attach(By.name("photo"), contactData.getPhoto());
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

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAcontact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void create(ContactData contact) {
    gotoContactPage();
    fillContactForm(contact);
    submitContactCreation();
    gotoHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    submitContactModification();
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmContactDeletion();
    gotoHomePage();
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
