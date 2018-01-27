package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX); //Делаем переменную app глобальной
  // т.е. доступной всем классам

  @BeforeSuite // Запускаем браузер один раз перед всеми нужными тестами
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite // Останаливаем браузер один раз после всех нужных тестов
    public void tearDown() {
    app.stop();
  }

}
