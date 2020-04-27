package ru.barancev.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.barancev.addressbook.appmanager.ApplicationManager;

public class TestBase {

    //protected final ApplicationManager app = new ApplicationManager();

//Лекция 6.10. Описание конфигурации тестового стенда
    //public static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);


    @BeforeSuite
    //@BeforeMethod

    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite
    //@AfterMethod

    public void tearDown() throws Exception {
        app.stop();
    }

}
