package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseHepler {

  WebDriver wd;

  public BaseHepler(WebDriver wd) { //Конструктор для иинициализации драйвера для Firefox
    this.wd = wd;
  }

  public void click (By locator) {  // Низкоуровневый метод нажатия на элемент
    wd.findElement(locator).click();
  }

  public void type (By locator, String text) { // Низкоуровневый метод очистки и заполнения поля
    click(locator); //Используем ранее созданный метод для нажатия на кнопку
     wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }

  public void attach (By locator, File file) { // Низкоуровневый метод очистки и заполнения поля
    if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath()); //getAbsolutePath - преобразование относительного пути в абсолютный
      }
    }

  public boolean isAlertPresent() { //Вынесен вспомогательный метод для обработки окна типа Alert
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}

