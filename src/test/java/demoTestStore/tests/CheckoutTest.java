package demoTestStore.tests;

import demoTestStore.Base;
import demoTestStore.pages.CheckoutCartPage;
import demoTestStore.pages.MainPage;
import demoTestStore.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(listeners.ListenerTestNG.class)

public class CheckoutTest extends Base {

    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutCartPage checkoutCartPage;

    @BeforeSuite
    public void setUp() throws IOException {
        initialize();
        getUrl();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        checkoutCartPage = new CheckoutCartPage(driver);
    }

    @Test
    //Add item to cart test
    public void checkout() throws IOException {
        mainPage.goToProductPage("Adventure");
        productPage.makeSelectionsForProduct(1, true);
        productPage.addProductToCart();
        productPage.proceedToCheckout();
        checkoutCartPage.addPromoCode();
        productPage.proceedToCheckout();
        checkoutCartPage.signInWhileInCart();
        checkoutCartPage.fillOutAddress();
        checkoutCartPage.fillOutShippingMethod();
        checkoutCartPage.fillOutPaymentMethod();
        checkoutCartPage.submitCheckout();
        Assert.assertTrue(checkoutCartPage.orderIsConfirmed.isDisplayed());
    }

    @AfterMethod
    public void logout() {
        mainPage.logOut();
        Assert.assertTrue(mainPage.userLogInButton.isDisplayed());
        System.out.println("User has logged out");
        driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() { closeDriver(); }

}