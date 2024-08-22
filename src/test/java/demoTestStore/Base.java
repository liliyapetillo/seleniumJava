package demoTestStore;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Base {

    public static WebDriver driver;
    private String url;
    private float elementFloatvalue;

    public WebDriver initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public String getUrl() throws IOException {
        Properties prop = new Properties();
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/java/demoTestStore/config.properties");
        prop.load(data);
        driver.get(prop.getProperty("url"));
        return url;
    }

    public String[] getUserProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/java/demoTestStore/config.properties");
        prop.load(data);
        String property[] = new String[2];
        property[0] = prop.getProperty("userEmail");
        property[1] = prop.getProperty("userPassword");
        return property;
    }

    public void waitForElementVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public void clickElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForElementClickable(element,5);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void selectFromDropdownByText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectFromDropdownByIndex(WebElement element, int index) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToTheBottomOfThePage() {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void findProduct(By elementBy, String containsText) {

        List<WebElement> list =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
        try {
            for (WebElement origin : list) {
                if (origin.getText().contains(containsText)) {
                    System.out.println(origin.getText());
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", origin);
                    waitForElementClickable(origin, 5);
                    origin.click();
                }
            }
        } catch (StaleElementReferenceException ex) {
            list = driver.findElements(elementBy);
        }
    }

    public float convertStringToFloat(WebElement element) {
        String elementPriceTextValue = element.getText();
        elementPriceTextValue = elementPriceTextValue.replace("$", "");
        elementFloatvalue = Float.parseFloat(elementPriceTextValue);
        return elementFloatvalue;
    }

    public void FailedScreenshot(String testMethodName) {
        System.out.println("Test has failed and this message is from the ITestListner interface");

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
        try {
            FileUtils.copyFile(srcFile, new File("./Screenshots/" + testMethodName + "_" + TimeStamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeDriver() {
        driver.quit();
    }
}

