package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Peter").withMiddlename("I")
                    .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
                    .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
                    .withGroup("test1"));
        }
    }

    @Test
    public void testDeleteContact() throws InterruptedException {
        //NavigationHelper.gotoHomePage();
        app.goTo().homePage();


        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        //int index = before.size() - 1;
        app.contact().delete(deletedContact);
        Thread.sleep(4000);
        Set<ContactData> after = app.contact().all();


        //Сравнение количества контактов до и после создания
        Assert.assertEquals(after.size(), before.size() - 1);

//Лекция 5.5. Повсеместное использование уникальных идентификаторов объектов
        before.remove(deletedContact);

        //before.remove(index);
        // Работает и без цикла
//        for (int i = 0; i<after.size(); i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        Assert.assertEquals(before, after);
    }


}
