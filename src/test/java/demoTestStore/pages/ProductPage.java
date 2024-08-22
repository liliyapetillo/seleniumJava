package demoTestStore.pages;

import demoTestStore.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class ProductPage extends Base {

    @FindBy(css = ".add-to-cart")
    public WebElement addToCartButton;

    @FindBy(css = ".product-variants .form-control.form-control-select")
    public WebElement productOptionsDropdown;

    @FindBy(css = "input[title='Black']")
    public WebElement selectColorBlack;

    @FindBy(css = "button.btn.btn-touchspin.js-touchspin.bootstrap-touchspin-up")
    public WebElement increaseQuantityButton;

    @FindBy(xpath = "//*[text() ='Proceed to checkout']")
    public WebElement proceedToCheckoutButton;

    @FindBy(css = "span.current-price-value")
    public WebElement spanCurrentPrice;

    @FindBy(css = "span[class*='subtotal']")
    public WebElement orderSubtotal;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private float currentPrice;
    private float discountPrice;
    private float itemsNumber;
    private float totalPrice;

    // Make selection of size and color on the Product page
    public float makeSelectionsForProduct(int dropdownIndex, boolean increaseQuantity) {
        selectFromDropdownByIndex(productOptionsDropdown, dropdownIndex);
        try {
            if (selectColorBlack.isDisplayed()) {
                selectColorBlack.click();
            }
        } catch (Exception e) {
            System.out.println("Color option is not available");
        }
        if (increaseQuantity){
            clickElement(increaseQuantityButton, 5);}
            convertStringToFloat(spanCurrentPrice);
            itemsNumber ++;
            System.out.println("current price " + currentPrice);
            System.out.println("items number " + itemsNumber);
        return currentPrice;
    }

    // Click Add to card button
    public void addProductToCart(){
        clickElement(addToCartButton, 5);
        waitForElementVisible(orderSubtotal,10);
        convertStringToFloat(orderSubtotal);
    }

    // Click Proceed to checkout button
    public void proceedToCheckout() {
        Assert.assertEquals(currentPrice*itemsNumber, totalPrice);
        clickElement(proceedToCheckoutButton, 5);
    }
}