package ru.barancev.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import ru.barancev.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //Задание №15: Реализовать проверку данных, загружаемых из БД
//        app.goTo().homePage();
//        if (app.contact().all().size() == 0) {
//            app.contact().create(new ContactData().withFirstname("Peter").withMiddlename("I")
//                    .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
//                    .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//                    .withGroup("test1"), true);
//        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            //Лекция 7.6. Связи между объектами
//            app.contact().create(new ContactData().withFirstname("Peter").withMiddlename("I")
//                    .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
//                    .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//                    .withGroup("test1"), true);
            app.contact().create(new ContactData().withFirstname("Peter").withMiddlename("I")
                    .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
                    .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
                    , true);
        }
    }


    @Test
    public void testContactModification(){
        File photo = new File("src/test/resources/logo.png");

        app.goTo().homePage();

        //Задание №15: Реализовать проверку данных, загружаемых из БД
        //Contacts before = app.contact().all();
        Contacts before = app.db().contacts();

        ContactData modifiedContact = before.iterator().next();
        //int index = before.size()-1;
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Peter93333").withMiddlename("I2").withLastname("Pen9222")
                .withNickname("PeterP2").withTitle("Mr").withCompany("Good Company")
                .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951").withPhoto(photo);

        //int before = app.getContactHelper().getContactsCount();
        //app.getNavigationHelper().gotoContactSelect(before - 1);

        app.goTo().homePage();
        app.contact().modify(contact);

        //Задание №15: Реализовать проверку данных, загружаемых из БД
        //Contacts after = app.contact().all();
        Contacts after = app.db().contacts();

        //int after = app.getContactHelper().getContactsCount();

        //Сравнение количества контактов до и после модификации
        assertEquals(after.size(), before.size());

        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //before.remove(modifiedContact);
        //before.add(contact);

//Лекция 5.5. Повсеместное использование уникальных идентификаторов объектов
//        // Сравнение сортированных списков
//        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        before.sort(byId);
//        after.sort(byId);

        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //Assert.assertEquals(before, after);

        //Сравнение множеств(сетов)
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        assertThat(after, equalTo(before.without(modifiedContact)
                .withAdded(contact)));
        verifyContactListInUi();

    }




}