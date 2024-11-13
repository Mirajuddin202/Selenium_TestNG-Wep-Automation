package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.EmployeeModel;
import setup.Setup;
import utlis.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PIMTestRunner extends Setup {

    LoginPage loginPage;
    PIMPage pimPage;

    @BeforeMethod(alwaysRun = true)
    public void doLogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test(priority = 1, groups = "smoke")
    public void checkEmployeeList() {
        pimPage = new PIMPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait until the left menu items are fully loaded and visible
        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.leftMenubar));

        if (pimPage.leftMenubar.size() > 1) {
            pimPage.leftMenubar.get(1).click();  // Click on the PIM menu
        } else {
            Assert.fail("The leftMenubar does not have enough elements.");
            return;
        }

        // Wait for 'Records Found' span element visibility after clicking
        List<WebElement> spanElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("oxd-text--span")));

        if (spanElements.size() > 12) {
            String messageActual = spanElements.get(12).getText();
            String messageExpected = "Records Found";
            Assert.assertTrue(messageActual.contains(messageExpected), "Expected message 'Records Found' not found.");
        } else {
            Assert.fail("Expected number of elements not found on the page.");
        }
    }

    @Test(priority = 2)
    public void addNewEmployee() throws IOException, ParseException {
        pimPage = new PIMPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.button));

        if (pimPage.button.size() > 2) {
            pimPage.button.get(2).click();  // Click on 'Add Employee' button
        } else {
            Assert.fail("The button list does not contain enough elements.");
            return;
        }

        // Enable the "Create Login Details" switch
        WebElement createLoginSwitch = wait.until(ExpectedConditions.elementToBeClickable(By.className("oxd-switch-input")));
        createLoginSwitch.click();

        // Generate fake data for the new employee
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String password = "P@ssword123";

        // Populate the EmployeeModel with generated data
        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastName(lastName);
        model.setUsername(username);
        model.setPassword(password);

        // Use PIMPage to create a new employee
        pimPage.createNewEmployee(model);

        // Verify the "Personal Details" header appears after adding a new employee
        WebElement headerTitleElem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']"))
        );

        String textTitle = headerTitleElem.getText();
        Assert.assertTrue(textTitle.contains("Personal Details"), "Expected header 'Personal Details' not found.");

        // Save employee details using Utils
        Utils.saveUsers(model);
    }
}