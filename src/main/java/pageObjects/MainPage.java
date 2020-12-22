package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageObject {

    @FindBy(id="log_out")
    protected WebElement logoutButton;

    @FindBy(xpath="//*[@id='user-options']/a")
    protected WebElement logoutTitle;

    @FindBy(xpath="//*[@id='header-details-user-fullname']//img")
    protected WebElement profileImg;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void pressProfileImage() {
        press(profileImg);
    }

    public void pressLogoutButton() { press(logoutButton); }

    public boolean validateLogoutButtonIsAvailable() {
        return validateIsEnabled(logoutButton);
    }

    public boolean validateLogoutTitle() { return validateIsEnabled(logoutTitle); }

    public void logout() {
        press(profileImg);
        press(logoutButton);
    }
}
