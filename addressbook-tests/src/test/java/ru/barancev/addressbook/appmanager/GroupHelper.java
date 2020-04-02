package ru.barancev.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.barancev.addressbook.model.GroupData;
import ru.barancev.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);

    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value ='" + id +"']")).click();

    }


    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //GroupData group = new GroupData().withId(id).withName(name) ;
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
// Метод для создания множества
//    public Set<GroupData> allSet() {
//        Set<GroupData> groups = new HashSet<>();
//        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements){
//            String name = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            //GroupData group = new GroupData().withId(id).withName(name) ;
//            groups.add(new GroupData().withId(id).withName(name));
//        }
//        return groups;
//    }

//Лекция 5.7. Кеширование результатов длительных операций
    private Groups groupCache = null;



// Изменение метода для Hamcrest
    public Groups allSet() {
        if(groupCache != null){
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //GroupData group = new GroupData().withId(id).withName(name) ;
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }


//    public void modify(int index, GroupData group) {
//        selectGroup(index);
//        initGroupModification();
//        fillGroupForm(group);
//        submitGroupModification();
//        returnToGroupPage();
//    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }


    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }
}
