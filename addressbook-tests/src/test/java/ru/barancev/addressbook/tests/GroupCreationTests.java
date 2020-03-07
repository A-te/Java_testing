package ru.barancev.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();

    GroupData group = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
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

    group.setId(after.stream().max((o1, o2) ->
            Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));


  }

}
