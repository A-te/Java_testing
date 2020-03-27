package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();

    //Формирование списка контактов до создания нового контакта
    List<ContactData> before = app.contact().list();

    //int before = app.getContactHelper().getContactsCount();

    ContactData contact = new ContactData().withFirstname("Peter").withMiddlename("I")
            .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
            .withGroup("test1");
    app.contact().create(contact);
    //Формирование списка контактов после создания нового контакта
    List<ContactData> after = app.contact().list();

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

    contact.withId(max);
    before.add(contact);

    //Сравнение через упорядоченные списки
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);

    //Сравнение через множества (сеты)
    //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }
}
