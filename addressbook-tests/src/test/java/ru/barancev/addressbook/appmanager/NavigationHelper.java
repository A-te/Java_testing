package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
      click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));

    }

    public void gotoContactEdit() {
        click(By.xpath("(//img[@alt='Edit'])"));
    }

    public void gotoContactSelect() {
        click(By.linkText("selected[]"));
    }

    public void gotoContactDelete() {
        click(By.xpath("(//input[@value='Delete'])"));
    }
}
