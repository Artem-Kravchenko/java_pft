package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHepler{

  private WebDriver wd;

  public NavigationHelper(ApplicationManager app) {
    super(app);
    wd = app.getDriver();
  }

  public void manage(){
    click(By.linkText("Manage"));
  }
  public void login(String username, String password){
    type(By.name("username"),username);
    click(By.xpath("//input[@value='Login']"));
    type(By.name("password"),password);
    click(By.xpath("//input[@value='Login']"));
  }

  public void usersTab(){
    click(By.linkText("Manage Users"));

  }

}
