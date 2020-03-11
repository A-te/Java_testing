package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.NewContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){

        if (! app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new NewContactData("Peter", "I",
                    "Pen", "PeterP", "Mr", "Good Company",
                    "5858 GoodGuy Street, London, England",
                    "455-566-5951", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<NewContactData> before = app.getContactHelper().getContactList();

        //int before = app.getContactHelper().getContactsCount();
        //app.getNavigationHelper().gotoContactSelect(before - 1);
        app.getNavigationHelper().gotoContactEdit(before.size()-1);
        NewContactData contact = new NewContactData(before.get(before.size()-1).getId(),"Peter333", "I2",
                "Pen2", "PeterP2", "Mr", "Good Company",
                "5858 GoodGuy Street, London, England", "455-566-5951",
                null);
        app.getContactHelper().fillContactFields(contact, false);
        app.getContactHelper().clickUpdateContact();
        NavigationHelper.gotoHomePage();
        List<NewContactData> after = app.getContactHelper().getContactList();

        //int after = app.getContactHelper().getContactsCount();

        //Сравнение количества контактов до и после модификации
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}