package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.barancev.addressbook.model.NewContactData;

public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteContact() {
        click(By.name("selected[]"));
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactFields(NewContactData newContact) {
        type(By.name("firstname"), newContact.getFirstname());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getLastname());
        type(By.name("nickname"), newContact.getNickname());
        type(By.name("title"), newContact.getTitle());
        type(By.name("company"), newContact.getCompany());
        type(By.name("address"), newContact.getAddress());
        type(By.name("home"), newContact.getHomePhone());

    }

    public void clickUpdateContact() {
        click(By.name("update"));
    }
}

