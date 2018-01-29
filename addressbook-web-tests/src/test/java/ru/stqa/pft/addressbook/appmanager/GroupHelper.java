package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectGroupById(int id) { //Функция с параметром index для выбора конкретного элемента
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click(); //Поиск всех элементов на странице и выбор конкретного по index'у
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }


  public void create(GroupData group) { // Функция для создания новой группы
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId()); //Выбор конкретного элемента множества по id
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAgroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() { //Метод который считает и возвращает количество элементов типа checkbox на странице Groups
    return wd.findElements(By.name("selected[]")).size(); // Поиск всех элементов и подсчёт их количества
  }

  private Groups groupCache = null;


  public Groups all() { // Метод для формирования множества всех созданных групп
    if (groupCache != null) {
      return new Groups(groupCache); //возвращение копии кэша списка всех групп
    }
    groupCache = new Groups(); // Создаём класс HashSet для работы со множеством
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //Находим все элементы типа "Группа" на странице
    for (WebElement element :  elements) { //В цикле перебираем все элементы полученного списка
      String name = element.getText(); //Получаем имя каждой группы
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //Получаем id каждой группы
      groupCache.add(new GroupData().withId(id).withName(name)); //Добавляем объект (Считанную группу) в множество
    }
    return new Groups(groupCache); //возвращение копии кэша списка всех групп
  }



}
