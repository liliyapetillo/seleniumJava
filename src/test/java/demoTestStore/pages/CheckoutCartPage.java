package demoTestStore.pages;

import com.github.javafaker.Faker;
import demoTestStore.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Locale;

public class CheckoutCartPage extends Base {

    @FindBy(xpath = "//a[@href='#promo-code']")
    public WebElement linkHavePromoCode;

    @FindBy(xpath = "//input[@class='promo-input']")
    public WebElement inputDiscountName;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(css = "li[class='cart-summary-line'] span[class='label']")
    public WebElement labelPromoCode;

    @FindBy(css = "a[aria-controls='checkout-login-form']")
    public WebElement linkShowLoginForm;

    @FindBy(id = "checkout-addresses-step")
    public WebElement sectionCheckoutAddressesStep;

    @FindBy(xpath = "//div[@class='address']")
    public WebElement existingAddressInfo;

    @FindBy(css = "#field-address1")
    public WebElement inputFieldAddress;

    @FindBy(id = "field-city")
    public WebElement inputFieldCity;

    @FindBy(id = "field-id_state")
    public WebElement selectStateDropdown;

    @FindBy(id = "field-postcode")
    public WebElement inputFieldPostcode;

    @FindBy(id = "field-phone")
    public WebElement inputFieldPhone;

    @FindBy(css = "button[name='confirm-addresses']")
    public WebElement buttonConfirmAddresses;
   
    @FindBy(id = "delivery_message")
    public WebElement textareaDeliveryMessage;

    @FindBy(name = "confirmDeliveryOption")
    public WebElement buttonConfirmDeliveryOption;

    @FindBy(css = "#checkout-payment-step")
    public WebElement checkoutPaymentStep;

    @FindBy(xpath = "//span[.='Pay by Check']")
    public WebElement inputCheckPayment;

    @FindBy(css = "input[id='conditions_to_approve[terms-and-conditions]']")
    public WebElement inputApproveTermsandConditions;

    @FindBy(xpath = "//button[normalize-space()='Place order']")
    public WebElement buttonPlaceOrder;

    @FindBy(css = ".card-title.h1")
    public WebElement orderIsConfirmed;

    public CheckoutCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        mainPage = new MainPage(driver);
    }

    private final MainPage mainPage;

    public void addPromoCode() {
        clickElement(linkHavePromoCode, 5);
        sendKeys(inputDiscountName, "20OFF");
        clickElement(submitButton, 5);
        waitForElementVisible(labelPromoCode, 10);
    }

    public void signInWhileInCart() throws IOException {
        clickElement(linkShowLoginForm, 5);
        mainPage.logIn();
    }

    public void fillOutAddress(){
        waitForElementVisible(sectionCheckoutAddressesStep, 5);
        try {
            if (existingAddressInfo.isDisplayed()) {
                clickElement(buttonConfirmAddresses, 5);
            }
        } catch (Exception e) {
            Faker faker = new Faker((new Locale("en-us")));
            sendKeys(inputFieldAddress, "" + faker.address().streetAddress());
            sendKeys(inputFieldCity, "" + faker.address().city());
            selectFromDropdownByText(selectStateDropdown, "New York");
            sendKeys(inputFieldPostcode, "" + faker.address().zipCodeByState("NY"));
            sendKeys(inputFieldPhone, "" + faker.phoneNumber());
            clickElement(buttonConfirmAddresses, 5);
        }
    }

    public void fillOutShippingMethod(){
        sendKeys(textareaDeliveryMessage,"Leave at the side door");
        clickElement(buttonConfirmDeliveryOption, 5);
    }

    public void fillOutPaymentMethod(){
        waitForElementVisible(checkoutPaymentStep, 5);
        clickElement(inputCheckPayment, 5);
        inputApproveTermsandConditions.click();
    }

    public void  submitCheckout(){
        clickElement(buttonPlaceOrder, 5);
        waitForElementVisible(orderIsConfirmed, 10);
    }
}