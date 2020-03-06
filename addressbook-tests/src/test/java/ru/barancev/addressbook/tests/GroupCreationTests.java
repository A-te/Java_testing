package ru.barancev.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

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


    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max){
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));


  }

}
