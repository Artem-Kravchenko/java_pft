package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void initContactModification(int index) {
    //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
    wd.findElements(By.xpath(".//td[8]/a/img")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact);
    submitContactModification();
    homePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    confirmContactDeletion();
    homePage();
  }


  public List<ContactData> list() {
    List<ContactData> contacs = new ArrayList<ContactData>(); // Создаём класс для ArrayList для работы со списком
    List<WebElement> elements = wd.findElements(By.name("entry")); //Находим все элементы типа "Контакты" на странице (По записям)
    for (WebElement element :  elements) { //В цикле перебираем все элементы полученного списка
      String lastname = element.findElement(By.xpath(".//td[2]")).getText(); // Считывание фамилии
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //Считывание имени

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждого контакта
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null); //Создаём объекты типа ContactData с прочитанными атрибутами
      contacs.add(contact); //Добавляем объект (Каждый считанный контакт) в список
    }
    return contacs;
  }

}
