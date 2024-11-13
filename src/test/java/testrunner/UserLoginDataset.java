package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.LoginDataset;
import setup.Setup;

import java.io.IOException;

import static utlis.Utils.readJSONData;

public class UserLoginDataset extends Setup {
    @Test(dataProvider = "LoginCSVData",dataProviderClass = LoginDataset.class)
    public void userLogin(String username,String password) throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);

        loginPage.doLogin(username,password);


    }
   @AfterTest
    public void logout(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogout();
    }
}
