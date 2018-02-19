package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.mantis.tests.RegistrationTests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private WebDriver wd; //Указываем тип интерфейса

  private String browser; //Поле (переменная) browser
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp; //Поле, которое нужно, чтобы ссылаться на помощник FtpHelper
  private MailHelper mailHelper; //Поле, которое нужно, чтобы ссылаться на помощник MailHelper
  private JamesHelper jamesHelper;
  private ResetPasswordHelper resetPassword;
  private DbHelper db;
  private NavigationHelper navigationHelper;

  public ApplicationManager(String browser) { //Конструктор класса ApplicationManager с параметром browser
    this.browser = browser;                   //(Через этот параметр передаётся конкретный драйвер)
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() { //Ленивая остановка драйвера
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() { //Ленивая инициализация объекта класса RegistrationHelper
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() { //Ленивая инициализация помощника FtpHepler (В тот момент, когда он нам будет нужен)
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }


  public WebDriver getDriver() { //Ленивая инициализация веб-драйвера (Чтобы драйвер инициализировался тогда, когда он нам нужен)
    if (wd == null) {
      if (browser.equals(BrowserType.FIREFOX)) { //Выбор браузера со сравнением через метод equals
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public ResetPasswordHelper resetPassword() {
    if(resetPassword==null) {
      resetPassword = new ResetPasswordHelper(this);
    }
    return resetPassword;
  }

  public DbHelper db(){
    if (db==null){
      db= new DbHelper(this);
    }
    return db;
  }

  public NavigationHelper goTo(){
    if(navigationHelper==null) {
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }


}
