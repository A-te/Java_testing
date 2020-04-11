package ru.barancev.addressbook.tests;


import net.bytebuddy.agent.builder.AgentBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;
import ru.barancev.addressbook.model.Groups;

import javax.management.DescriptorKey;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});

//    list.add(new Object[] {"test1", "header1","footer1"});
//    list.add(new Object[] {"test2", "header2","footer2"});
//    list.add(new Object[] {"test3", "header3","footer3"});

    return list.iterator();
  }

  @Test(dataProvider = "validGroups")

  public void testGroupCreation(GroupData group) throws Exception {

//public void testGroupCreation(String name, String header, String footer) throws Exception {

      //Лекция 6.4. Параметризация тестовых методов(Продвинутый метод, with DataProvider(TestNG):
    //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);

    app.goTo().groupPage();
    Groups before = app.group().allSet();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().allSet();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) ->
            g.getId()).max().getAsInt()))));
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
