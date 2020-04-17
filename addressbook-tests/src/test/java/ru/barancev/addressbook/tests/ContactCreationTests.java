package ru.barancev.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.ContactData;
import ru.barancev.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json ="";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());

    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }


//  @DataProvider
//  public Iterator<Object[]> validContacts(){
//    List<Object[]> list = new ArrayList<Object[]>();
//    File photo = new File("src/test/resources/logo.png");
//    list.add(new Object[] {new ContactData().withFirstname("firstname11").withMiddlename("I")
//            .withLastname("lastname11").withNickname("nickname11").withTitle("Mr").withCompany("Good Company")
//            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//            .withGroup("test1").withPhoto(photo)});
//    list.add(new Object[] {new ContactData().withFirstname("firstname22").withMiddlename("I")
//            .withLastname("lastname22").withNickname("nickname22").withTitle("Mr").withCompany("Good Company")
//            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//            .withGroup("test1").withPhoto(photo)});
//    list.add(new Object[] {new ContactData().withFirstname("firstname33").withMiddlename("I")
//            .withLastname("lastname33").withNickname("nickname33").withTitle("Mr").withCompany("Good Company")
//            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//            .withGroup("test1").withPhoto(photo)});
////    list.add(new Object[] {"firstname2", "lastname2", "nickname2"});
////    list.add(new Object[] {"firstname3", "lastname3", "nickname3"});
//    return list.iterator();
//  }


  @Test(dataProvider = "validContactsFromJson")
//public void testContactCreation(String firstname,String lastname,String nickname) throws Exception {

  public void testContactCreation(ContactData contact) throws Exception {
    File photo = new File("src/test/resources/logo.png");
    contact.withMiddlename("I").withTitle("Mr").withCompany("Good Company")
            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
            .withGroup("test1").withPhoto(photo);

    //app.goTo().homePage();
    //ContactData contact = new ContactData().withFirstname(firstname)
    //        .withLastname(lastname).withNickname(nickname);

    //Лекция 6.4. Параметризация тестовых методов, part 2:
//    File photo = new File("src/test/resources/logo.png");
//    ContactData contact = new ContactData().withFirstname(firstname).withMiddlename("I")
//            .withLastname(lastname).withNickname(nickname).withTitle("Mr").withCompany("Good Company")
//            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//            .withGroup("test1").withPhoto(photo);

    app.goTo().homePage();

    //Формирование списка контактов до создания нового контакта
   // Set<ContactData> before = app.contact().all();

    Contacts before = app.contact().all();

    //int before = app.getContactHelper().getContactsCount();

    //Лекция 6.4. Параметризация тестовых методов
    //File photo = new File("src/test/resources/logo.png");
//    ContactData contact = new ContactData().withFirstname("Peter").withMiddlename("I")
//            .withLastname("Pen").withNickname("PeterP").withTitle("Mr").withCompany("Good Company")
//            .withAddress("5858 GoodGuy Street, London, England").withHomePhone("455-566-5951")
//            .withGroup("test1").withPhoto(photo);

//    ContactData contact = new ContactData().withFirstname(firstname)
//            .withLastname(lastname).withNickname(nickname);


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
