package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseHepler {
  FirefoxDriver wd;

  public BaseHepler(FirefoxDriver wd) {
    this.wd=wd;
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void gotoContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
  }
}
