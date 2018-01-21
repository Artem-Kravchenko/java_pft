package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  @Override //Переопределение
  public boolean equals(Object o) { //Метод для сравнения двух объектов типа GroupData
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

  @Override // Переопределение
  public String toString() { // Метод для вывода текстового представления элемента (атрибута объекта группы) для "Имени группы"
    return "GroupData{" +
            "name='" + name + '\'' +
            '}';
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
