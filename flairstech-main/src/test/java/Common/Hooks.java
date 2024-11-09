package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static Common.Properties.URL;

public class Hooks {
    public static WebDriver driver;

    public void startDriverSession() {
        try {
            WebDriver webDriver = initializeDriver();
            configureDriver(webDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start WebDriver session", e);
        }
    }


    private WebDriver initializeDriver() {
        switch (Properties.browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + Properties.browser);
        }
        return driver;
    }


    private void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
