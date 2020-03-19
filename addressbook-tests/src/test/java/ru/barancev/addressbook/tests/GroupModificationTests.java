package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();

        if (app.group().allSet().size() == 0) {
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification(){

        //int before = app.getGroupHelper().getGroupCount();

        Set<GroupData> before = app.group().allSet();
        GroupData groupToModify = before.iterator().next();

        //int index = before.size() - 1;

        GroupData group = new GroupData().withId(groupToModify.getId()).withName("test11")
                .withHeader("test22").withFooter("test33");
        app.group().modify(group);

        //int after = app.getGroupHelper().getGroupCount();

        Set<GroupData> after = app.group().allSet();
        Assert.assertEquals(after.size(),before.size());

        before.remove(groupToModify);
        before.add(group);

        // Сравнение через множества(сеты)
       // Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

        // Сравнение через отсортированные списки
        //Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);

        Assert.assertEquals(after, before);

    }



}
