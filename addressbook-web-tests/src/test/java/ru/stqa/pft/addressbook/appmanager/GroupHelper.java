package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHepler{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"),groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) { //Функция с параметром index для выбора конкретного элемента
    wd.findElements(By.name("selected[]")).get(index).click(); //Поиск всех элементов на странице и выбор конкретного по index'у
    }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }


  public void createGroup(GroupData group) { // Функция для создания новой группы
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAgroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() { //Метод который считает и возвращает количество элементов типа checkbox на странице Groups
    return wd.findElements(By.name("selected[]")).size(); // Поиск всех элементов и подсчёт их количества
  }

  public List<GroupData> getGroupList() { // Метод для формирования списка всех созданных групп
    List<GroupData> groups = new ArrayList<GroupData>(); // Создаём класс для ArrayList для работы со списком
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //Находим все элементы типа "Группа" на странице
    for (WebElement element :  elements) { //В цикле перебираем все элементы полученного списка
      String name = element.getText(); //Получаем имя каждой группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждой группы
      GroupData group = new GroupData(id, name, null, null); //Создаём объекты типа GroupData с прочитанными именами групп
      groups.add(group); //Добавляем объект (Считанную группу) в список
    }
    return groups;
  }
}
