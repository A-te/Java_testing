package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.NewContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0) {
            app.contact().create(new NewContactData("Peter", "I",
                    "Pen", "PeterP", "Mr", "Good Company",
                    "5858 GoodGuy Street, London, England",
                    "455-566-5951", "test1"), true);
        }
    }

    @Test
    public void testDeleteContact() throws InterruptedException {
        //NavigationHelper.gotoHomePage();
        app.goTo().homePage();


        List<NewContactData> before = app.contact().list();

        int index = before.size() - 1;
        app.contact().delete(index);
        Thread.sleep(4000);
        List<NewContactData> after = app.contact().list();


        //Сравнение количества контактов до и после создания
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        // Работает и без цикла
//        for (int i = 0; i<after.size(); i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        Assert.assertEquals(before, after);
    }


}
