package demoTestStore.pages;

import com.github.javafaker.Faker;
import demoTestStore.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends Base {
    @FindBy(id = "_desktop_user_info")
    public WebElement userLogInButton;

    @FindBy(css = "a[data-link-action='display-register-form']")
    public WebElement linkDisplayRegisterForm;

    @FindBy(id = "field-firstname")
    public WebElement inputFieldFirstname;

    @FindBy(id = "field-lastname")
    public WebElement inputFieldLastname;

    @FindBy(id = "field-email")
    public WebElement inputFieldEmailSignUpPage;

    @FindBy(id = "field-password")
    public WebElement inputFieldPassword;

    @FindBy(id = "field-birthday")
    public WebElement inputFieldBirthday;

    @FindBy(xpath = "//label[.//input[@name='psgdpr']]")
    public WebElement labelAgreeTheTermsAndConditions;

    @FindBy(css = "button[data-link-action='save-customer']")
    public WebElement buttonSaveCustomer;

    @FindBy(css = ".logout")
    public WebElement linkSignOut;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signUp() {
        Faker faker = new Faker();
        clickElement(userLogInButton,5);
        clickElement(linkDisplayRegisterForm, 5);
        sendKeys(inputFieldFirstname, "" + faker.name().firstName());
        sendKeys(inputFieldLastname, "" + faker.name().lastName());
        sendKeys(inputFieldEmailSignUpPage, ("" + faker.internet().emailAddress()));
        sendKeys(inputFieldPassword, ("" + faker.internet().password()));
        sendKeys(inputFieldBirthday, "05/28/" + faker.number().numberBetween(1920, 1999));
        clickElement(labelAgreeTheTermsAndConditions, 5);
        clickElement(buttonSaveCustomer, 5);
        waitForElementVisible(linkSignOut, 10);
    }

    public void logOut() {
        clickElement(linkSignOut, 5);
        waitForElementVisible(userLogInButton, 10);
    }

}