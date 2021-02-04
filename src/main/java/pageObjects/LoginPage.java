package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Util;

public class LoginPage extends BasePageObject {

    @FindBy(id = "login-form-username")
    protected WebElement username;

    @FindBy(id = "login-form-password")
    protected WebElement password;

    @FindBy(id = "login")
    protected WebElement loginButton;

    @FindBy(id = "usernameerror")
    protected WebElement usernameError;

    @FindBy(id = "create_link")
    protected WebElement createLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String validateErrorMessage() {
        return validateAssertEquals(usernameError);
    }

    public void pressLoginButton() {
        press(loginButton);
    }

    public void openBaseUrl() {
        goToUrl(Util.BASE_URL);
    }

    public void enterValidUsername() {
        enter(username, Util.USERNAME);
    }

    public void enterInvalidPassword() {
        enter(password, "badTry");
    }

    public void login() {
        goToUrl(Util.BASE_URL);
        enter(username, Util.USERNAME);
        enter(password, Util.PASSWORD);
        press(loginButton);
        waitForVisibility(createLink);
    }
}
