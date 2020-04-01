package ru.barancev.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import ru.barancev.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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


        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        //int index = before.size() - 1;
        app.contact().delete(deletedContact);
        Thread.sleep(4000);
        Contacts after = app.contact().all();


        //Сравнение количества контактов до и после создания
        assertEquals(after.size(), before.size() - 1);

//Лекция 5.5. Повсеместное использование уникальных идентификаторов объектов
        //before.remove(deletedContact);

        //before.remove(index);
        // Работает и без цикла
//        for (int i = 0; i<after.size(); i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
