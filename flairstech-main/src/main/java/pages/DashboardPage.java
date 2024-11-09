package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BaseTest {
    private final By loginHeaderText = By.xpath("(//*[normalize-space()='Login'])[1]");
    private final By usernameField = By.xpath("//*[@name='username']");
    private final By passwordField = By.xpath("//*[@name='password']");
    private final By submitLoginBtn= By.xpath("(//*[normalize-space()='Login'])[3]");
    private final By dashboardTitle = By.xpath("(//*[normalize-space()='Dashboard'])[5]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void checkLoginScreen() {
        waitForVisibilityOfElement(loginHeaderText);
        ElementsValidator(usernameField, passwordField, submitLoginBtn);
    }

    public void checkLoginSuccessfully(String username, String password) {
        sendTextToInputField(username, usernameField);
        sendTextToInputField(password, passwordField);
        clickOnElement(submitLoginBtn);
    }

    public void checkDashboardDisplayed() {
        waitForVisibilityOfElement(dashboardTitle);
    }

}
