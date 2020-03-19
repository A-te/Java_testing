package ru.barancev.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    Set<GroupData> before = app.group().allSet();
    //int before = app.getGroupHelper().getGroupCount();

    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    app.group().create(group);
    Set<GroupData> after = app.group().allSet();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(),before.size() + 1);


//    int max = 0;
//    for (GroupData g : after) {
//      if (g.getId() > max){
//        max = g.getId();
//      }
//    }

//    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };


//    Comparator<? super GroupData> byId =
//            (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

//    int max1 = after.stream().max((o1, o2) ->
//            Integer.compare(o1.getId(), o2.getId())).get().getId();



    // Сравнение множеств
//    group.setId(after.stream().max((o1, o2) ->
//            Integer.compare(o1.getId(), o2.getId())).get().getId());
//    before.add(group);
//    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));



    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);

    // Сортировка больше не нужна, начинаем использовать множества и поиск элемента по id
//    Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);

    Assert.assertEquals(before, after);


  }

}
