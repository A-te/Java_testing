package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.appmanager.NavigationHelper;
import ru.barancev.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        NavigationHelper.homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

//        Лекция 5.11. Клеим строки: метод обратных проверок
//        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
//        assertThat(contact.getMobilePhone() , equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
//        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
//        assertThat(contact.getMobilePhone() , equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
//        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

    }

    private String mergePhones(ContactData contact) {
        //Слишком простой способ:
//        String result = "";
//        if (contact.getHomePhone() != null){
//            result = result + contact.getHomePhone();
//        }

        //Способ, с использованием функционального проектирования:
        String result = Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),
                contact.getWorkPhone()).stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests :: cleaned)
                .collect(Collectors.joining("\n"));
        return result;
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]", "");
    }
}
