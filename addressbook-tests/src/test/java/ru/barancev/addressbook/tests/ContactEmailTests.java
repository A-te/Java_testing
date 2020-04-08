package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @Test
    public void testContactPhones() {
        NavigationHelper.homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        //assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditForm.getEmail()));

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {

        String result = Arrays.asList(contact.getEmail(),contact.getEmail2(),
                contact.getEmail3()).stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests :: cleaned)
                .collect(Collectors.joining("\n"));
        return result;
    }

    public static String cleaned(String email){
        return email.replaceAll("\\s","");
    }
}
