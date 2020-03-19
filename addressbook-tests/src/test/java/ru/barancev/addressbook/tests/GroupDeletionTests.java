package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();

        if (app.group().allSet().size() == 0) {
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }


    @Test
    public void testDeleteGroup() throws Exception {

        //int before = app.getGroupHelper().getGroupCount();
        Set<GroupData> before = app.group().allSet();

        GroupData groupToDelete = before.iterator().next();

        //int index = before.size() - 1;

        app.group().delete(groupToDelete);

        //int after = app.getGroupHelper().getGroupCount();

        Set<GroupData> after = app.group().allSet();
        Assert.assertEquals(after.size(), before.size() - 1);

      //  before.remove(index);
        before.remove(groupToDelete);


//        for (int i = 0; i < before.size() - 1; i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }

        Assert.assertEquals(before, after);
    }



}
