package ru.barancev.addressbook.tests;

import org.testng.annotations.Test;
import ru.barancev.addressbook.model.NewContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFields(new NewContactData("Peter", "I", "Pen", "PeterP", "Mr", "Good Company", "5858 GoodGuy Street, London, England", "455-566-5951"));
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().deleteNewContact();
  }


}
