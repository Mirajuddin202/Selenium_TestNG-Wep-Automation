package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utlis.Utils;

import java.io.IOException;
import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeMethod(groups = "smoke")
    public void setup() {
        // Set up ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterMethod(groups = "smoke")
    public void takeScreenshotWhenFailed(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) ;
        Utils.takeScreenshot(driver);
    }

    @AfterMethod(groups = "smoke")
    public void closeBrowser() {
        // Quit the driver after each test method
        if (driver != null) {
            driver.quit();
        }
    }
}
