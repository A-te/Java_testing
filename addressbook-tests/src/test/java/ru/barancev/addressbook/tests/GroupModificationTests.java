package ru.barancev.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.barancev.addressbook.model.GroupData;
import ru.barancev.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //Set<GroupData> before = app.group().allSet();
        Groups before = app.group().allSet();
        GroupData groupToModify = before.iterator().next();

        //int index = before.size() - 1;

        GroupData group = new GroupData().withId(groupToModify.getId()).withName("test11")
                .withHeader("test22").withFooter("test33");
        app.group().modify(group);
        //Хеширование(Предварительная проверка при помощи более быстрой операции):
        assertThat(app.group().count(), equalTo(before.size()));

        //int after = app.getGroupHelper().getGroupCount();

        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //Set<GroupData> after = app.group().allSet();

        Groups after = app.group().allSet();
//Лекция 5.8. Хеширование и предварительные проверки (Переносим проверку выше, перед выполнением длительной операции)
        //assertEquals(after.size(),before.size());

//Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
//        before.remove(groupToModify);
//        before.add(group);
//
//        // Сравнение через множества(сеты)
//       // Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
//
//        // Сравнение через отсортированные списки
//        //Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(), g2.getId());
//        //before.sort(byId);
//        //after.sort(byId);
//
//        Assert.assertEquals(after, before);
        assertThat(after, equalTo(before.withOut(groupToModify).withAdded(group)));

    }



}
