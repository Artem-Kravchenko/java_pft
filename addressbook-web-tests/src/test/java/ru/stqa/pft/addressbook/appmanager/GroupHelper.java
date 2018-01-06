package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {

  private BaseHepler baseHepler;

  public GroupHelper(FirefoxDriver wd) {
    baseHepler = new BaseHepler(wd);
  }

  public void fillGroupForm(GroupData groupData) {
    baseHepler.wd.findElement(By.name("group_name")).click();
    baseHepler.wd.findElement(By.name("group_name")).clear();
    baseHepler.wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    baseHepler.wd.findElement(By.name("group_header")).click();
    baseHepler.wd.findElement(By.name("group_header")).clear();
    baseHepler.wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    baseHepler.wd.findElement(By.name("group_footer")).click();
    baseHepler.wd.findElement(By.name("group_footer")).clear();
    baseHepler.wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  public BaseHepler getBaseHepler() {
    return baseHepler;
  }
}
