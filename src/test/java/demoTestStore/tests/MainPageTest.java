package demoTestStore.tests;

import demoTestStore.Base;
import demoTestStore.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

@Listeners(listeners.ListenerTestNG.class)

public class MainPageTest extends Base {

    private MainPage mainPage;

    @BeforeSuite
    public void setUp() throws IOException {
        initialize();
        getUrl();
        mainPage = new MainPage(driver);
    }

    @Test
    public void logIn() throws IOException {
        mainPage.goToLoginPage();
        mainPage.logIn();
        Assert.assertTrue(mainPage.linkSignOut.isDisplayed());
        System.out.println("User has logged in");
    }

    @AfterSuite
    public void logout() {
        mainPage.logOut();
        Assert.assertTrue(mainPage.userLogInButton.isDisplayed());
        System.out.println("User has logged out");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() { closeDriver(); }

}