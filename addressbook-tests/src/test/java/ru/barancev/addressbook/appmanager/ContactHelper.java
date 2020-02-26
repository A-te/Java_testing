package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.barancev.addressbook.model.NewContactData;

public class ContactHelper extends BaseHelper {
    

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteNewContact() {
      wd.findElement(By.name("selected[]")).click();
      wd.findElement(By.xpath("//input[@value='Delete']")).click();

      wd.switchTo().alert().accept();
    }

    public void submitNewContact() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void gotoAddNewContact() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void fillContactFields(NewContactData newContact) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(newContact.getFirstname());
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(newContact.getMiddlename());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(newContact.getLastname());
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(newContact.getNickname());
      wd.findElement(By.name("title")).click();
      wd.findElement(By.name("title")).clear();
      wd.findElement(By.name("title")).sendKeys(newContact.getTitle());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(newContact.getCompany());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(newContact.getAddress());
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(newContact.getHomePhone());
    }
}
