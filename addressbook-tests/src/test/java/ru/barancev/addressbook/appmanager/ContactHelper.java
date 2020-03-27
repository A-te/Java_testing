package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.barancev.addressbook.model.NewContactData;
import java.util.ArrayList;
import java.util.List;



public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteContact(int index) {
        //Выбираем элемент по индексу в списке
        wd.findElements(By.name("selected[]")).get(index).click();

        // Выбираем первый элемент
        //click(By.name("selected[]"));

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

    public void create(NewContactData newContact, boolean creation) {
        NavigationHelper.gotoAddNewContact();
        fillContactFields (newContact, true);
        submitNewContact();
        NavigationHelper.homePage();
    }

    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();

    }
    // метод создания списка обьектов, имеющих имя и фамилию, из имеющихся контактов
    public List<NewContactData> list() {
        List<NewContactData> contacts = new ArrayList<>();
        //List<WebElement> elements = wd.findElements(By.xpath("(//table[@id='maintable']/tbody/tr)"));
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            //String ids = element.findElement(By.tagName("input")).getAttribute("value");
            //int id = Integer.parseInt(ids);

            // Или можно сразу вычленять инт
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            NewContactData contact = new NewContactData(id, firstname, null, lastname,
                    null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    public void modify(int index, NewContactData contact) {
        NavigationHelper.gotoContactEdit(index);
        fillContactFields(contact, false);
        clickUpdateContact();
        NavigationHelper.homePage();
    }

    public void delete(int index) {
        deleteContact(index);
        NavigationHelper.homePage();
    }

    public void create(NewContactData contact) {
        NavigationHelper.gotoAddNewContact();
        fillContactFields(contact, true);
        submitNewContact();
        NavigationHelper.homePage();
    }
}

