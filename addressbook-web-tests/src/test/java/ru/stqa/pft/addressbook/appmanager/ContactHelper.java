package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {

  private BaseHepler baseHepler;

  public ContactHelper(FirefoxDriver wd) {
      baseHepler = new BaseHepler(wd);
  }

  public void fillContactForm(ContactData contactData) {
    baseHepler.wd.findElement(By.name("firstname")).click();
    baseHepler.wd.findElement(By.name("firstname")).clear();
    baseHepler.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    baseHepler.wd.findElement(By.name("lastname")).click();
    baseHepler.wd.findElement(By.name("lastname")).clear();
    baseHepler.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    baseHepler.wd.findElement(By.name("address")).click();
    baseHepler.wd.findElement(By.name("address")).clear();
    baseHepler.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    baseHepler.wd.findElement(By.name("mobile")).click();
    baseHepler.wd.findElement(By.name("mobile")).clear();
    baseHepler.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhoneNumber());
    baseHepler.wd.findElement(By.name("email")).click();
    baseHepler.wd.findElement(By.name("email")).clear();
    baseHepler.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  public BaseHepler getBaseHepler() {
    return baseHepler;
  }

}
