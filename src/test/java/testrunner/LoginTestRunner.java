package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.Setup;

import java.time.Duration;

public class LoginTestRunner extends Setup {

    LoginPage loginPage;

    @Test(priority = 1, description = "Admin cannot login with invalid credentials")
    public void doLoginIfWrongCreds() {
        loginPage = new LoginPage(driver);


        String loginTitleActual=driver.findElement(By.className("orangehrm-login-title")).getText();
        Assert.assertTrue(loginTitleActual.contains("Login"));
        Assert.assertTrue(driver.findElement(By.className("orangehrm-login-branding")).isDisplayed());

        loginPage.doLogin("wronguser", "wrongpass");
        WebElement alertTextElem = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertTextElem.getText(), "Invalid credentials");
    }


    @Test(priority = 2, description = "Admin can login with valid credentials", groups = "smoke")
    public void doLogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        //loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        Assert.assertTrue(loginPage.btnProfileImage.isDisplayed(), "Profile image is not displayed.");
    }


    @Test(priority = 3, description = "Admin can logout by clicking on logout button", groups = "smoke")
    public void doLogout() {
        loginPage.doLogout();
        WebElement forgotPasswordLinkElem = driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLinkElem.isDisplayed(), "Forgot Password link is not displayed after logout.");
    }

}