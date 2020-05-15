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

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){


        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }

        //app.goTo().groupPage();
//        if (app.group().allSet().size() == 0) {
//            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
//        }
    }


    @Test
    public void testDeleteGroup() throws Exception {

        //int before = app.getGroupHelper().getGroupCount();

        //Задание №15: Реализовать проверку данных, загружаемых из БД
        //Groups before = app.group().allSet();
        Groups before = app.db().groups();

        GroupData groupToDelete = before.iterator().next();

        //int index = before.size() - 1;

        app.goTo().groupPage();
        app.group().delete(groupToDelete);

        //int after = app.getGroupHelper().getGroupCount();

        //Хеширование(Предварительная проверка прри помощи более быстрой операции):
        assertThat(app.group().count(), equalTo(before.size()-1));

        //Задание №15: Реализовать проверку данных, загружаемых из БД
        //Groups after = app.group().allSet();
        Groups after = app.db().groups();

        //Лекция 5.8. Хеширование и предварительные проверки(переносим проверку выше)
        //assertEquals(after.size(), before.size() - 1);

      //  before.remove(index);
        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //before.remove(groupToDelete);


//        for (int i = 0; i < before.size() - 1; i++){
//            Assert.assertEquals(before.get(i), after.get(i));
//        }
        //Лекция 5.6. Hamcrest: улучшение внешнего вида проверок
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(groupToDelete)));
        verifyGroupListInUi();
    }



}
