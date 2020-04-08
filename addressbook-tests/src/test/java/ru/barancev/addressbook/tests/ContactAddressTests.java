package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @Test
    public void testContactPhones() {
        NavigationHelper.homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }
}
