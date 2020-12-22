import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import pageObjects.LoginPage;
import pageObjects.MainPage;

import java.net.MalformedURLException;
import java.net.URL;

public class TestScript {

    private WebDriver driver;

    LoginPage login;
    MainPage mainPage;

    public TestScript() {
    }

    @Parameters({"Port"})
    @BeforeClass
    public void initiateDriver(String Port) throws MalformedURLException {
        if (Port.equalsIgnoreCase("9001")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new ChromeOptions());
        } else if (Port.equalsIgnoreCase("9002")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new FirefoxOptions());
        }
        login = new LoginPage(driver);
        mainPage = new MainPage(driver);

        login.openBaseUrl();
        mainPage.resetSidebar();
        login.openBaseUrl();
    }

    @AfterClass
    public void cleanUp () {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

        @Test
        public void successfulLogin () {
            login.login();
            mainPage.pressProfileImage();
            Assert.assertTrue("logout button should be available", mainPage.validateLogoutButtonIsAvailable());
            mainPage.pressLogoutButton();
        }

        @Test
        public void loginWithoutUserInfos () {
            login.openBaseUrl();
            login.pressLoginButton();
            Assert.assertEquals(login.validateErrorMessage(), "Sorry, your username and password are incorrect - please try again.");
        }

        @Test
        public void loginWithoutPassword () {
            login.openBaseUrl();
            login.enterValidUsername();
            login.pressLoginButton();
            Assert.assertEquals(login.validateErrorMessage(), "Sorry, your username and password are incorrect - please try again.");
        }

        @Test
        public void loginWithInvalidPassword () {
            login.openBaseUrl();
            login.enterValidUsername();
            login.enterInvalidPassword();
            login.pressLoginButton();
            Assert.assertEquals(login.validateErrorMessage(), "Sorry, your username and password are incorrect - please try again.");
        }

        @Test
        public void successfulLogout () {
            login.login();
            mainPage.logout();
            Assert.assertTrue("a login button should be available", mainPage.validateLogoutTitle());
        }
    }
