package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    //Формирование списка контактов до создания нового контакта
    List<NewContactData> before = app.getContactHelper().getContactList();

    //int before = app.getContactHelper().getContactsCount();

    app.getNavigationHelper().gotoAddNewContact();
    NewContactData contact = new NewContactData("Peter", "I",
            "Pen", "PeterP", "Mr", "Good Company",
            "5858 GoodGuy Street, London, England",
            "455-566-5951", "test1");
    app.getContactHelper().fillContactFields(contact, true);
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().gotoHomePage();

    //Формирование списка контактов после создания нового контакта
    List<NewContactData> after = app.getContactHelper().getContactList();


    //int after = app.getContactHelper().getContactsCount();

    //Сравнение количества контактов до и после создания
    //Assert.assertEquals(after, before + 1);

    Assert.assertEquals(after.size(), before.size() + 1);


    //app.getContactHelper().deleteContact();

    // Обычный цикл для нахождения наибольшего значения id
//    int max = 0;
//    for (NewContactData x : after){
//      if (x.getId() > max){
//        max = x.getId();
//      }
//    }

//    // Возможности Java 6 для нахождения наибольшего значения id
//    Comparator<? super NewContactData> byId = new Comparator<NewContactData>() {
//      @Override
//      public int compare(NewContactData o1, NewContactData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };
//    int max = after.stream().max(byId).get().getId();


    // Возможности Java 8 для нахождения наибольшего значения id
//    Comparator<? super NewContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//    int max = after.stream().max(byId).get().getId();


    // Возможности Java 9 для нахождения наибольшего значения id
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    contact.setId(max);
    before.add(contact);

    //Сравнение через упорядоченные списки
    Comparator<? super NewContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);

    //Сравнение через множества (сеты)
    //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));


  }


}
