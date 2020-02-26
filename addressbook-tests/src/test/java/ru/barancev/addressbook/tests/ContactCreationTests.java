package ru.barancev.addressbook.tests;

import org.testng.annotations.*;
import ru.barancev.addressbook.appmanager.ApplicationManager;
import ru.barancev.addressbook.model.NewContactData;

public class ContactCreationTests {

  private final ApplicationManager app = new ApplicationManager();

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFields(new NewContactData("Peter", "I", "Pen", "PeterP", "Mr", "Good Company", "5858 GoodGuy Street, London, England", "455-566-5951"));
    app.getContactHelper().submitNewContact();
    app.gotoHomePage();
    app.getContactHelper().deleteNewContact();
  }


  public ApplicationManager getApp() {
    return app;
  }
}
