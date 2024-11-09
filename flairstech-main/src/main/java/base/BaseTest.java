package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTest {
    public WebDriver driver;

    public BaseTest(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOnElement(By by) {
        waitForVisibilityOfElement(by);
        driver.findElement(by).click();

    }


    public void waitForVisibilityOfElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    public Boolean assertElementDisplayed(By by) {
        waitForVisibilityOfElement(by);
        return driver.findElement(by).isDisplayed();
    }


    public void scrollToElement(By element) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(element));
    }

    public void sendTextToInputField(String text, By by) {
        waitForVisibilityOfElement(by);
        clearInputField(by);
        driver.findElement(by).sendKeys(text);
    }


    public void clearInputField(By by) {
        driver.findElement(by).clear();
    }


    public static String generateRandomDigits(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length should be greater than 0");
        }

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }


    public void waitForTime(int timeIntoMilSec) {
        try {
            Thread.sleep(timeIntoMilSec);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void ElementsValidator(By... screenLocators) {
        for (By screenLocator : screenLocators) {
            scrollToElement(screenLocator);
            Assert.assertTrue(assertElementDisplayed(screenLocator));
        }
    }


    public int extractNumberOfRecords(String text) {
        Pattern pattern = Pattern.compile("\\(?(\\d+)\\)?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }


}
