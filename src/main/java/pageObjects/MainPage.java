package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageObject {

    @FindBy(id = "log_out")
    protected WebElement logoutButton;

    @FindBy(xpath = "//*[@id='user-options']/a")
    protected WebElement logoutTitle;

    @FindBy(xpath = "//*[@id='header-details-user-fullname']//img")
    protected WebElement profileImg;

    @FindBy(xpath = "//*[@id='content']//a[2]")
    private WebElement expandSidebar;

    @FindBy(xpath = "//*[@id='content']/div[1]//header/div/div[2]//a")
    private WebElement projectName;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void pressProfileImage() {
        press(profileImg);
    }

    public void pressLogoutButton() {
        press(logoutButton);
    }

    public boolean validateLogoutButtonIsAvailable() {
        return validateIsEnabled(logoutButton);
    }

    public boolean validateLogoutTitle() {
        return validateIsEnabled(logoutTitle);
    }

    private boolean projectNameVisible() {
        return projectName.isDisplayed();
    }

    public void clickOnExpandSidebar() {
        press(expandSidebar);
    }

    public void resetSidebar() {
        if (!projectNameVisible()) {
            clickOnExpandSidebar();
        }
        waitForVisibility(projectName);
        logout();
    }

    public void logout() {
        press(profileImg);
        press(logoutButton);
    }
}
