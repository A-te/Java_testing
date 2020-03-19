package ru.barancev.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification(){

        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("test11")
                .withHeader("test22").withFooter("test33");
        app.group().modify(index, group);

        //int after = app.getGroupHelper().getGroupCount();
        List<GroupData> after = app.group().list();
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
