package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Util {

    public static final String CHROME_DRIVER_LOCATION = "C://Users//merva//chromedriver_win32//chromedriver.exe";
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final WebDriver DRIVER = new ChromeDriver();
    public static final String USERNAME = System.getenv("jiraUsername");
    public static final String PASSWORD = System.getenv("jiraPassword");
    public static final String BASE_URL = System.getenv("jiraBaseUrl");
    public static final String openIssuesUrl = System.getenv("jiraOpenIssuesUrl");
    public static final String testIssueUrl = System.getenv("jiraTestIssueUrl");
}
