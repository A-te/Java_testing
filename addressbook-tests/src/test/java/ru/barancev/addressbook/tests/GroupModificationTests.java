package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1",
                    "test2", "test3"));
        }
    }

    @Test
    public void testGroupModification(){

        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.getGroupHelper().getGroupList();

        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(),"test11", "test22",
                "test33");
        app.getGroupHelper().modifyGroup(index, group);

        //int after = app.getGroupHelper().getGroupCount();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(index);
        before.add(group);
        // Сравнение через множества(сеты)
       // Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

        // Сравнение через отсортированные списки
        Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }



}
