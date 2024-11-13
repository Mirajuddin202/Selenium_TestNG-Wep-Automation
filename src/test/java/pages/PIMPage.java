package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.EmployeeModel;

import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> leftMenubar;

    @FindBy(className = "oxd-button")
    public List<WebElement> button;

    @FindBy(className = "oxd-input")
    public List<WebElement> textField;

    public PIMPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNewEmployee(EmployeeModel model) {
        // Fill in the employee details using data from the model
        textField.get(1).sendKeys(model.getFirstname());
        textField.get(3).sendKeys(model.getLastName());
        textField.get(5).sendKeys(model.getUsername());
        textField.get(6).sendKeys(model.getPassword());
        textField.get(7).sendKeys(model.getPassword());

        // Click the save button to add the employee
        button.get(1).click();
    }
}