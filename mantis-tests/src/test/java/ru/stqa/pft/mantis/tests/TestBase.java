package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //Делаем переменную app глобальной
  // т.е. доступной всем классам

  @BeforeSuite // Запускаем браузер один раз перед всеми нужными тестами
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true) // Останаливаем браузер один раз после всех нужных тестов
    public void tearDown() {
    app.stop();
  }

 }
