package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd; //Указываем тип интерфейса

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser; //Поле (переменная) browser

  public ApplicationManager(String browser) { //Конструктор класса ApplicationManager с параметром browser
    this.browser = browser;                   //(Через этот параметр передаётся конкретный драйвер)
  }


  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) { //Выбор браузера со сравнением через метод equals
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);

    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    contactHelper.wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}
