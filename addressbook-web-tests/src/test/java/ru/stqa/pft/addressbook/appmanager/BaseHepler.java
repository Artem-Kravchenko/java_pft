package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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
  /*  if (text != null) { //Проверка поля на наличие текста
      String existingText = wd.findElement(locator).getAttribute("value"); //Получаем значения поля ввода
       if (!text.equals(existingText)) { // Проверка совпадает ли введённый текст с вводимым
    */  wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
  //  }
 // }

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

