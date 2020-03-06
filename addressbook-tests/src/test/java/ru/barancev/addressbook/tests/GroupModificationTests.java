package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1",
                    "test2", "test3"));
        }
        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test11", "test22",
                "test33");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        //int after = app.getGroupHelper().getGroupCount();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size()-1);
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
