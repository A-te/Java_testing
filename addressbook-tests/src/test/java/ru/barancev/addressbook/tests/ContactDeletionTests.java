package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testDeleteContact(){
        app.getContactHelper().deleteContact();

    }
}
