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
    contactCache = null;
    homePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact);
    submitContactModification();
    contactCache = null;
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmContactDeletion();
    contactCache = null;
    homePage();
  }

  private Contacts contactCache = null;


  public Contacts all() { // Функция для работы с множеством элементов
    if (contactCache != null){
      return new Contacts(contactCache); //возвращение копии кэша списка всех групп
    }
    contactCache = new Contacts(); //Создаём объект для работы с множеством элементов
    List<WebElement> elements = wd.findElements(By.name("entry")); //Находим все элементы типа "Контакты" на странице (По записям)
    for (WebElement element :  elements) { //В цикле перебираем все элементы полученного списка
      String lastname = element.findElement(By.xpath(".//td[2]")).getText(); // Считывание фамилии
      String firstname = element.findElement(By.xpath(".//td[3]")).getText(); //Считывание имени
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждого контакта
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)); //Добавляем объект (Каждый считанный контакт) в список
    }
    return new Contacts(contactCache); //возвращение копии кэша списка всех групп
  }

  public ContactData infoFromEditForm(ContactData contact) {  //Функция считывания данных из формы редактирования контакта
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }
}
