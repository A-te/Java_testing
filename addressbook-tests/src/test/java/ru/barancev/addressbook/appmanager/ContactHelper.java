package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

    public void fillContactFields(NewContactData newContact, boolean creation) {
        type(By.name("firstname"), newContact.getFirstname());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getLastname());
        type(By.name("nickname"), newContact.getNickname());
        type(By.name("title"), newContact.getTitle());
        type(By.name("company"), newContact.getCompany());
        type(By.name("address"), newContact.getAddress());
        type(By.name("home"), newContact.getHomePhone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void clickUpdateContact() {
        click(By.name("update"));
    }

    public boolean isContactPresent() {
        return (isElementPresent(By.name("selected[]")));
//        return (isElementPresent(By.name("selected[]"))
//                && isElementPresent(By.xpath("(//a[contains(text(),'Last name')]")));
    }

    public void createContact(NewContactData newContact, boolean creation) {
        NavigationHelper.gotoAddNewContact();
        fillContactFields (newContact, true);
        submitNewContact();
        NavigationHelper.gotoHomePage();
    }
}

