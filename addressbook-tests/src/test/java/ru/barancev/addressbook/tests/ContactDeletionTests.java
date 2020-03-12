package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.NewContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testDeleteContact() throws InterruptedException {
        //NavigationHelper.gotoHomePage();
        app.getNavigationHelper().gotoHomePage();


        if (! app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new NewContactData("Peter", "I",
                    "Pen", "PeterP", "Mr", "Good Company",
                    "5858 GoodGuy Street, London, England",
                    "455-566-5951", "test1"), true);
        }

        List<NewContactData> before = app.getContactHelper().getContactList();

        //int before = app.getContactHelper().getContactsCount();
        app.getContactHelper().deleteContact(before.size() - 1);
        NavigationHelper.gotoHomePage();
        Thread.sleep(4000);

        List<NewContactData> after = app.getContactHelper().getContactList();

        //int after = app.getContactHelper().getContactsCount();

        //Сравнение количества контактов до и после создания
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        // Работает и без цикла
//        for (int i = 0; i<after.size(); i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        Assert.assertEquals(before, after);
    }
}
