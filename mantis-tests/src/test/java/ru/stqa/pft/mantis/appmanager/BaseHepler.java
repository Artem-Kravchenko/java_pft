package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseHepler {

  protected ApplicationManager app;
  protected WebDriver wd;

  public BaseHepler(ApplicationManager app) { //Конструктор для иинициализации драйвера для Firefox
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void click(By locator) {  // Низкоуровневый метод нажатия на элемент
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) { // Низкоуровневый метод очистки и заполнения поля
    click(locator); //Используем ранее созданный метод для нажатия на кнопку
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) { // Низкоуровневый метод очистки и заполнения поля
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

