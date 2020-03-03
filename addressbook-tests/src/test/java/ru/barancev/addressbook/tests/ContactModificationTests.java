package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactEdit();
        app.getContactHelper().fillContactFields(new NewContactData("Peter2", "I2",
                "Pen2", "PeterP2", "Mr", "Good Company",
                "5858 GoodGuy Street, London, England", "455-566-5951",
                null), false);
        app.getContactHelper().clickUpdateContact();
        app.getNavigationHelper().gotoHomePage();

    }
}
