package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1",
                    "test2", "test3"));
        }
    }


    @Test
    public void testDeleteGroup() throws Exception {

        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        app.group().delete(index);

        //int after = app.getGroupHelper().getGroupCount();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
//        for (int i = 0; i < before.size() - 1; i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }

        Assert.assertEquals(before, after);
    }



}
