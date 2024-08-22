package demoTestStore.tests;

import demoTestStore.Base;
import demoTestStore.pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(listeners.ListenerTestNG.class)

public class SignUpPageTest extends Base {

    private SignUpPage signUpPage;

    @BeforeSuite
    public void setUp() throws IOException {
        initialize();
        getUrl();
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void signup() {
        signUpPage.signUp();
        Assert.assertTrue(signUpPage.linkSignOut.isDisplayed());
    }

    @AfterMethod
    public void logout(){
        signUpPage.logOut();
        System.out.println("User has logged out");
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }

}
