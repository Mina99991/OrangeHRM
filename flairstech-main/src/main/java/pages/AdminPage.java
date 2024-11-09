package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AdminPage extends BaseTest {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    String randomPassword = "Password@" + generateRandomDigits(2);
    String username = "MinaMaher" + generateRandomDigits(2);
    Actions actions = new Actions(driver);

    int numberOfRecordsBeforeAddUser = 0;
    int numberOfRecordsAfterAddUser = 0;
    int numberOfRecordsBeforeDelete = 0;
    int numberOfRecordsAfterDelete = 0;
    private final By adminButtonFromSideMenu = By.xpath("(//*[normalize-space()='Admin'])[2]");
    private final By numberOfRecordsFoundLabel = By.xpath("(//*[contains(normalize-space(),' Records Found')])[11]");
    private final By addRecordBtn = By.xpath("//button[contains(normalize-space(),'Add')]");
    private final By selectUserRoleDropdown = By.xpath("(//*[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    private final By selectStatus = By.xpath("(//*[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    private final By employeeNameField= By.xpath("//*[@placeholder='Type for hints...']");
    private final By usernameField = By.xpath("(//*[@autocomplete='off'])[1]");
    private final By passwordField = By.xpath("(//*[@autocomplete='off'])[2]");
    private final By confirmPasswordField = By.xpath("(//*[@autocomplete='off'])[3]");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save']");
    private final By deleteBtn = By.xpath("//*[@class='oxd-icon bi-trash']");
    private final By searchByUserNameField = By.xpath("(//*[@class='oxd-input oxd-input--active'])[2]");
    private final By confirmDeleteBtn = By.xpath("//*[normalize-space()='Yes, Delete']");
    private final By searchBtn = By.xpath("//*[@type='submit' and contains(normalize-space(),'Search')]");
    private final By resetFieldsBtn =By.xpath("//button[normalize-space()='Reset']");
    public void checkRecordsScreen() {
        clickOnElement(adminButtonFromSideMenu);
        waitForVisibilityOfElement(numberOfRecordsFoundLabel);
        ElementsValidator(numberOfRecordsFoundLabel, addRecordBtn);
    }

    public void checkRecordCreation(String employeeName) {
        numberOfRecordsBeforeAddUser = extractNumberOfRecords(driver.findElement(numberOfRecordsFoundLabel).getText());
        clickOnElement(addRecordBtn);
        selectRule();
        selectEmployeeName(employeeName);
        selectStatus();
        sendTextToInputField(username, usernameField);
        sendTextToInputField(randomPassword, passwordField);
        sendTextToInputField(randomPassword, confirmPasswordField);
        clickOnElement(saveBtn);
        waitForVisibilityOfElement(numberOfRecordsFoundLabel);
        numberOfRecordsAfterAddUser = extractNumberOfRecords(driver.findElement(numberOfRecordsFoundLabel).getText());

    }

    public void validateRecordCreatedSuccessfully() {
        Assert.assertTrue(numberOfRecordsAfterAddUser > numberOfRecordsBeforeAddUser);
        System.out.println("before Add record= " + numberOfRecordsBeforeAddUser + ", after Add record=" + numberOfRecordsAfterAddUser);
    }

    private void selectEmployeeName(String employeeName) {
        sendTextToInputField(employeeName, employeeNameField);
        waitForTime(5000);
        waitForVisibilityOfElement(By.xpath("//*[normalize-space()='" + employeeName + "']"));
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();

    }

    private void selectRule() {
        clickOnElement(selectUserRoleDropdown);
        waitForTime(5000);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
    }

    private void selectStatus() {
        actions.moveToElement(driver.findElement(selectStatus)).click().perform();
        waitForTime(5000);
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void validateDeleteRecordsFunctionality() {
        numberOfRecordsBeforeDelete = extractNumberOfRecords(driver.findElement(numberOfRecordsFoundLabel).getText());
        waitForVisibilityOfElement(searchByUserNameField);
        sendTextToInputField(username, searchByUserNameField);
        clickOnElement(searchBtn);
        waitForVisibilityOfElement(deleteBtn);
        clickOnElement(deleteBtn);
        clickOnElement(confirmDeleteBtn);
        waitForVisibilityOfElement(numberOfRecordsFoundLabel);
        clickOnElement(resetFieldsBtn);
        waitForVisibilityOfElement(numberOfRecordsFoundLabel);
        numberOfRecordsAfterDelete = extractNumberOfRecords(driver.findElement(numberOfRecordsFoundLabel).getText());
    }

    public void validateDeletingSuccessfully() {
        Assert.assertTrue(numberOfRecordsBeforeDelete > numberOfRecordsAfterDelete);
        System.out.println("before Delete record= " + numberOfRecordsBeforeDelete + ", after Delete record=" + numberOfRecordsAfterDelete);

    }

}
