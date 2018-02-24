package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //Делаем переменную app глобальной
  // т.е. доступной всем классам

  @BeforeSuite // Запускаем браузер один раз перед всеми нужными тестами
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true) // Останаливаем браузер один раз после всех нужных тестов
    public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }


  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException { // Проверка статуса Issue
    String issueStatus = app.soap().getIssueStatusById(issueId);
    if (issueStatus.equals("resolved") || issueStatus.equals("closed")) {
      return false;
    } else
      return true;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}




