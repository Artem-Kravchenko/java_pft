package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHepler {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhoneNumber());
    type(By.name("email"), contactData.getEmail());
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
    homePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    submitContactModification();
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmContactDeletion();
    homePage();
  }

  public Contacts all() { // Функция для работы с множеством элементов
    Contacts contacs = new Contacts(); //Создаём объект для работы с множеством элементов
    List<WebElement> elements = wd.findElements(By.name("entry")); //Находим все элементы типа "Контакты" на странице (По записям)
    for (WebElement element :  elements) { //В цикле перебираем все элементы полученного списка
      String lastname = element.findElement(By.xpath(".//td[2]")).getText(); // Считывание фамилии
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //Считывание имени
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждого контакта
      contacs.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)); //Добавляем объект (Каждый считанный контакт) в список
    }
    return contacs;
  }

}
