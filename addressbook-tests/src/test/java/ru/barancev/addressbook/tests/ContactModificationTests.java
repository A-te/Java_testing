package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Peter").withMiddlename("I")
                    .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
                    .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
                    .withGroup("test1"), true);
        }
    }


    @Test
    public void testContactModification(){

        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData().withId(before.get(index).getId())
                .withFirstname("Peter3333").withMiddlename("I2").withLastname("Pen222")
                .withNickname("PeterP2").withTitle("Mr").withCompany("Good Company")
                .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951");

        //int before = app.getContactHelper().getContactsCount();
        //app.getNavigationHelper().gotoContactSelect(before - 1);

        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();

        //int after = app.getContactHelper().getContactsCount();

        //Сравнение количества контактов до и после модификации
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        // Сравнение сортированных списков
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        //Сравнение множеств(сетов)
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}