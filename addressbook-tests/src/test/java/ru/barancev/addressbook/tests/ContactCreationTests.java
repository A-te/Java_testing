package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import ru.barancev.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();

    //Формирование списка контактов до создания нового контакта

   // Set<ContactData> before = app.contact().all();
    Contacts before = app.contact().all();


    //int before = app.getContactHelper().getContactsCount();

    File photo = new File("src/test/resources/logo.png");
    ContactData contact = new ContactData().withFirstname("Peter").withMiddlename("I")
            .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
            .withGroup("test1").withPhoto(photo);
    app.contact().create(contact);

    //Формирование списка контактов после создания нового контакта
    //Set<ContactData> after = app.contact().all();
    Contacts after = app.contact().all();


    //int after = app.getContactHelper().getContactsCount();
    //Сравнение количества контактов до и после создания
    //Assert.assertEquals(after, before + 1);

//Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
//    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after.size(), equalTo(before.size()+1));



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
//   int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    //Лекция 5.5. Повсеместное использование уникальных идентификаторов объектов
    //Используем другой способ для нахождения максимального Id:
    int max = after.stream().mapToInt((c) -> c.getId()).max().getAsInt();
    contact.withId(max);

    //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
    //before.add(contact);

//Лекция 5.5. Повсеместное использование уникальных идентификаторов объектов.Сортировать множество не надо
//    //Сравнение через упорядоченные списки
//    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);

//Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
//    Assert.assertEquals(after, before);
    assertThat(after, equalTo(before.withAdded(contact)));

    //Сравнение через множества (сеты)
    //Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

  //Тест для определения директорий
//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/logo.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//
//  }

}
