package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public static void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }

    public static void gotoAddNewContact() {
        click(By.linkText("add new"));

    }

    public void gotoContactEdit(int index) {
        wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();

        //click(By.xpath("(//img[@alt='Edit'])"));
    }

    public void gotoContactSelect(int index) {
        //Поиск элемента по индексу списка
        wd.findElements(By.name("selected[]")).get(index).click();

        //Выбор первого на странице элемента
        //click(By.xpath("(//tr[5]//td[1]//input[1])"));
    }

    public void gotoContactDelete() {
        click(By.xpath("(//input[@value='Delete'])"));
    }
}
