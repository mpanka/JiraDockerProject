package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

    protected WebDriver driver;

    @FindBy(id = "summary")
    protected WebElement summary;

    @FindBy(id = "summary-val")
    protected WebElement summaryValue;

    @FindBy(id = "create_link")
    protected WebElement createLink;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForVisibility(WebElement element) throws Error {
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement webElement) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    protected void goToUrl(String url) {
        driver.get(url);
        maximizeWindow();
    }

    protected void enter(WebElement element, String data) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(data);
    }

    protected void press(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected String validateAssertEquals(WebElement element) {
        try {
            waitForVisibility(element);
            return element.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    protected boolean validateIsEnabled(WebElement element) {
        try {
            waitForVisibility(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
