package Tests;

import Pages.LogInPage;
import Utils.SeleniumHelper;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;


public class LogInPageTest extends BaseTest {


    @Test
    public void SmokeLoginTest() throws IOException {
        driver.get("https://dev.datalexing.sa/en/login/");
        ExtentTest test = extentReports.createTest("Smoke login text");
        LogInPage logInPage = new LogInPage(driver);
        logInPage.enterEmail("testoritoto@mail.ru");
        test.log(Status.PASS,"Email is entered",SeleniumHelper.getScreenshot(driver));
        logInPage.enterPassword("Testowanie999!!!");
        test.log(Status.PASS,"Password is entered",SeleniumHelper.getScreenshot(driver));
        logInPage.logInButtonClick();
        test.log(Status.PASS,"User is logged in",SeleniumHelper.getScreenshot(driver));
    }
    @Test
    public void FieldsValidationTest(){
        driver.get("https://dev.datalexing.sa/en/login/");
        LogInPage logInPage = new LogInPage(driver);
        logInPage.logInButtonClick();
        Assert.assertEquals(logInPage.getEmailErrorInputText(),"Email is a required field");
        Assert.assertEquals(logInPage.getPasswordErrorInputText(),"Password is a required field");
        logInPage.enterEmail("q");
        logInPage.enterPassword("1234567");
        Assert.assertEquals(logInPage.getEmailErrorInputText(),"Email must be a valid email");
        Assert.assertEquals(logInPage.getPasswordErrorInputText(),"must be at least 8");
        logInPage.enterEmail("@m.com");
        logInPage.absenceOfEmailInputError(driver);
        logInPage.clearPassword();
        logInPage.enterPassword("12345678");
        logInPage.absenceOfPasswordInputError(driver);
        logInPage.enterPassword("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
        Assert.assertEquals(logInPage.getPasswordErrorInputText(),"must be smaller than 257");
        logInPage.clearPassword();
        logInPage.enterPassword("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456");
        logInPage.absenceOfPasswordInputError(driver);
        logInPage.logInButtonClick();
        Assert.assertEquals(logInPage.getTextOfPopUpError(),"No active account found with the given credentials.");


    }
    @Test
    public void popUpTest() throws InterruptedException {

        driver.get("https://dev.datalexing.sa/en/login/");
        LogInPage logInPage = new LogInPage(driver);
        logInPage.enterEmail("q@mdsrsвtdsg.rsey");
        logInPage.enterPassword("123456789");
        logInPage.logInButtonClick();
        Assert.assertEquals(logInPage.getTextOfPopUpError(),"No active account found with the given credentials.");
        logInPage.closePopUpError(driver);
        logInPage.absenceOfPopUpError();

//        //DODELAJ



    }

}
