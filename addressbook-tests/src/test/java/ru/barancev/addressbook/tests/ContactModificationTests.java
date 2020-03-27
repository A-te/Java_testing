package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification(){

        app.goTo().homePage();
        List<NewContactData> before = app.contact().list();
        int index = before.size()-1;
        NewContactData contact = new NewContactData(before.get(index).getId(),"Peter3333", "I2",
                "Pen222", "PeterP2", "Mr", "Good Company",
                "5858 GoodGuy Street, London, England", "455-566-5951",
                null);

        //int before = app.getContactHelper().getContactsCount();
        //app.getNavigationHelper().gotoContactSelect(before - 1);

        app.contact().modify(index, contact);
        List<NewContactData> after = app.contact().list();

        //int after = app.getContactHelper().getContactsCount();

        //Сравнение количества контактов до и после модификации
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        // Сравнение сортированных списков
        Comparator<? super NewContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        //Сравнение множеств(сетов)
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}