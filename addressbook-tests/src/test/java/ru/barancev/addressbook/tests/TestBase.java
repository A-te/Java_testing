package ru.barancev.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.barancev.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);



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

    @AfterSuite(alwaysRun = true)
    //@AfterMethod
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test" + m.getName() + "with parameters" + Arrays.asList(p));

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test" + m.getName());

    }

}
