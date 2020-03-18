package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1",
                    "test2", "test3"));
        }
    }


    @Test
    public void testDeleteGroup() throws Exception {

        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();

        //int after = app.getGroupHelper().getGroupCount();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(before.size() - 1);
//        for (int i = 0; i < before.size() - 1; i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }

        Assert.assertEquals(before, after);
    }

}
