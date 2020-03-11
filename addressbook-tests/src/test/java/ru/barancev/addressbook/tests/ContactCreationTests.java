package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    //Формирование списка контактов до создания нового контакта
    List<NewContactData> before = app.getContactHelper().getContactList();

    //int before = app.getContactHelper().getContactsCount();

    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFields(new NewContactData("Peter", "I",
            "Pen", "PeterP", "Mr", "Good Company",
            "5858 GoodGuy Street, London, England",
            "455-566-5951", "test1"), true);
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().gotoHomePage();

    //Формирование списка контактов после создания нового контакта
    List<NewContactData> after = app.getContactHelper().getContactList();


    //int after = app.getContactHelper().getContactsCount();

    //Сравнение количества контактов до и после создания
    //Assert.assertEquals(after, before + 1);
    Assert.assertEquals(after.size(), before.size() + 1);


    //app.getContactHelper().deleteContact();


  }


}
