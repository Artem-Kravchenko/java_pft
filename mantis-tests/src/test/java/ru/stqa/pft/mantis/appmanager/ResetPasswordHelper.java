package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordHelper extends BaseHepler {

  private  ApplicationManager app;
  private WebDriver wd;

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
    wd =app.getDriver();
  }

  public void select(String username){
    click(By.linkText(username));
  }

  public void init() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }

}

