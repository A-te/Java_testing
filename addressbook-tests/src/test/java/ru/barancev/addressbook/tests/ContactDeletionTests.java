package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testDeleteContact(){
        if (! app.getContactHelper().isContactPresent()) {
            app.getContactHelper().createContact(new NewContactData("Peter", "I",
                    "Pen", "PeterP", "Mr", "Good Company",
                    "5858 GoodGuy Street, London, England",
                    "455-566-5951", "test1"), true);
        }
        app.getContactHelper().deleteContact();

    }
}
