package demoTestStore.pages;

import demoTestStore.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;


public class MainPage extends Base {

    WebDriver driver;

    @FindBy(css = "form#login-form #field-email")
    public WebElement inputFieldEmailLogInPage;

    @FindBy(css = "form#login-form #field-password")
    public WebElement inputFieldPasswordLogInPage;

    @FindBy(css = "#_desktop_user_info")
    public WebElement userLogInButton;

    @FindBy(css = "form#login-form button[type=\"submit\"]")
    public WebElement submitLoginButton;

    @FindBy(xpath = "//div[@class='products row']/div")
    public WebElement productMiniature1;

    By productMiniature = By.xpath("//div[@class='products row']/div");

    @FindBy(css = ".logout")
    public WebElement linkSignOut;


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void goToLoginPage() {
        clickElement(userLogInButton, 5);
    }

    public void logIn() throws IOException {
        sendKeys(inputFieldEmailLogInPage, getUserProperties()[0]);
        sendKeys(inputFieldPasswordLogInPage, getUserProperties()[1]);
        clickElement(submitLoginButton,5);
    }

    // Go to All Products page and find product with specific text
    public void goToProductPage(String containsText) {
        waitForElementVisible(productMiniature1, 5);
        findProduct(productMiniature, containsText);
    }

    public void logOut() {
        clickElement(linkSignOut, 5);
        waitForElementVisible(userLogInButton, 10);
    }
}