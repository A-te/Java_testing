package ru.barancev.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;
import ru.barancev.addressbook.model.Groups;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.FilterAttachable;
import ch.qos.logback.core.spi.LifeCycle;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


  // Загрузка данных из файла формата XML
  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    //Лекция 6.8. Автоматическое закрытие файлов после использования
    try(BufferedReader reader = new BufferedReader(new FileReader(new File
            ("src/test/resources/groups.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    //Лекция 6.8. Автоматическое закрытие файлов после использования
//    BufferedReader reader = new BufferedReader(new FileReader(new File
//            ("src/test/resources/groups.xml")));
//    String xml = "";
//    String line = reader.readLine();
//    while (line != null) {
//      xml += line;
//      line = reader.readLine();
//    }
//    XStream xstream = new XStream();
//    xstream.processAnnotations(GroupData.class);
//    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
//    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {

    try(BufferedReader reader = new BufferedReader(new FileReader(new File
            ("src/test/resources/groups.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }



//  //Лекция 6.5. Загрузка тестовых данных из List
//  @DataProvider
//  public Iterator<Object[]> validGroupsFromList(){
//    List<Object[]> list = new ArrayList<>();
//    list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
//    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
//    list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
//
//    list.add(new Object[] {"test1", "header1","footer1"});
//    list.add(new Object[] {"test2", "header2","footer2"});
//    list.add(new Object[] {"test3", "header3","footer3"});
//
//    return list.iterator();
//  }
//
//  //Лекция 6.5. Загрузка тестовых данных из файла CSV
//  @DataProvider
//  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
//    List<Object[]> list = new ArrayList<Object[]>();
//    BufferedReader reader = new BufferedReader(new FileReader(new File
//            ("src/test/resources/groups.csv")));
//    String line = reader.readLine();
//    while (line != null){
//      String[] split = line.split(";");
//      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
//      line = reader.readLine();
//    }
//    return list.iterator();


  @Test(dataProvider = "validGroupsFromJson")

  public void testGroupCreation(GroupData group) throws Exception {
//public void testGroupCreation(String name, String header, String footer) throws Exception {

      //Лекция 6.4. Параметризация тестовых методов(Продвинутый метод, with DataProvider(TestNG):
    //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);

    app.goTo().groupPage();

    //Задание №15: Реализовать проверку данных, загружаемых из БД
    //Groups before = app.group().allSet();
    Groups before = app.db().groups();

    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));

    //Задание №15: Реализовать проверку данных, загружаемых из БД
    //Groups after = app.group().allSet();
    Groups after = app.db().groups();

    //Задание №15: Реализовать проверку данных, загружаемых из БД (для дебаггинга)
//    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) ->
//            g.getId()).max().getAsInt()))));

    Groups beforePlusAdded = before.withAdded(group.withId(after.stream().mapToInt((g) ->
            g.getId()).max().getAsInt()));

    assertThat(after, equalTo(beforePlusAdded));
  }

//    //Лекция 6.4. Параметризация тестовых методов(примитивный метод):
//    String[] names = new String[] {"test1","test2","test3"};
//    for (String name : names){
//      GroupData group = new GroupData().withName(name);
//      app.goTo().groupPage();
//      Groups before = app.group().allSet();
//      app.group().create(group);
//      assertThat(app.group().count(), equalTo(before.size()+1));
//      Groups after = app.group().allSet();
//      assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) ->
//              g.getId()).max().getAsInt()))));
//    }

////Лекция 6.4. Параметризация тестовых методов
//    app.goTo().groupPage();
//    Groups before = app.group().allSet();
//    //int before = app.getGroupHelper().getGroupCount();
//
//    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
//    app.group().create(group);
//    //Хеширование(Предварительная проверка прри помощи более быстрой операции):
//    assertThat(app.group().count(), equalTo(before.size()+1));
//    Groups after = app.group().allSet();
//
//    //int after = app.getGroupHelper().getGroupCount();
//
//    //Assert.assertEquals(after.size(),before.size() + 1);
//
////Лекция 5.8. Хеширование и предварительные проверки(переносим проверку выше)
//    //assertThat(after.size(), equalTo(before.size() + 1));
//
//
////    int max = 0;
////    for (GroupData g : after) {
////      if (g.getId() > max){
////        max = g.getId();
////      }
////    }
//
////    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
////      @Override
////      public int compare(GroupData o1, GroupData o2) {
////        return Integer.compare(o1.getId(), o2.getId());
////      }
////    };
//
//
////    Comparator<? super GroupData> byId =
////            (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//
////    int max1 = after.stream().max((o1, o2) ->
////            Integer.compare(o1.getId(), o2.getId())).get().getId();
//
//
//
//    // Сравнение множеств
////    group.setId(after.stream().max((o1, o2) ->
////            Integer.compare(o1.getId(), o2.getId())).get().getId());
////    before.add(group);
////    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
//
//
//// Лекция 5.6. Hamcrest: улучшение внешнего вида проверок. Переносим в одну строчку(ниже) ~Fluent interface
////    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
//
//    //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
//    //before.add(group);
//
//    // Сортировка больше не нужна, начинаем использовать множества и поиск элемента по id
////    Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(), g2.getId());
////    before.sort(byId);
////    after.sort(byId);
//
//    // Assert.assertEquals(before, after);
//
//    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) ->
//            g.getId()).max().getAsInt()))));
//
//  }

  @Test
  public void testBadGroupCreation() throws Exception {

    app.goTo().groupPage();
    Groups before = app.group().allSet();
    GroupData group = new GroupData().withName("test1'");
    app.group().create(group);
    //Хеширование(Предварительная проверка прри помощи более быстрой операции):
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().allSet();
//Лекция 5.8. Хеширование и предварительные проверки
    //assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }

}
