package Tests;

import Pages.SignUpPage;
import Utils.SeleniumHelper;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpPageTest extends BaseTest {

    @Test
    public void Test(){
        String emailRandom = "testowaror" + (int) (Math.random() *1000) + "@gmail.com";
        driver.get("https://dev.datalexing.sa/en/register/");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage
                .enterFullName("name")
                .enterEmail(emailRandom)
                .enterPassword("Testowanie999!!!")
                .enterPhoneNumber("123454443")
                .clickTermPrivacyButton();
        signUpPage.clickSignUpButton();
    }

    @Test
    public void ErrorTest(){
        driver.get("https://dev.datalexing.sa/en/register/");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.clickSignUpButton();

        Assert.assertEquals(signUpPage.getEmailError(),"Email is a required field");
        Assert.assertEquals(signUpPage.getFullNameError(),"Name is a required field");
        Assert.assertEquals(signUpPage.getPasswordError(),"Password is a required field");
        Assert.assertEquals(signUpPage.getPhoneNumberError(),"Phone number is a required field");
        Assert.assertEquals(signUpPage.getCheckBoxError(),"must be accepted");

        signUpPage.enterFullName("a")
                .enterEmail("a")
                .enterPassword("a")
                .enterPhoneNumber("4")
                .clickTermPrivacyButton();
        signUpPage.clickSignUpButton();

        Assert.assertEquals(signUpPage.getEmailError(),"Email must be a valid email");
        Assert.assertEquals(signUpPage.getFullNameError(),"must be at least 2");
        Assert.assertEquals(signUpPage.getPasswordError(),"must be at least 8");
        Assert.assertEquals(signUpPage.getPhoneNumberError(),"Invalid number. Number length should be between 7 - 15 digits");

        signUpPage.enterFullName("!")
                .enterEmail("qwerty@mail.ru")
                .enterPassword("234567")
                .enterPhoneNumber("234")
                .waitForEmailErrorToDisappear()
                .waitForPhoneNumberErrorToDisappear()
                .waitForTermsCheckBoxErrorToDisappear();

        Assert.assertEquals(signUpPage.getFullNameError(),"Contains invalid characters");
        Assert.assertEquals(signUpPage.getPasswordError(),"must be at least 8");

        signUpPage.clearFullName()
                .enterFullName("qwr")
                .enterPassword("8")
                .waitForFullNameErrorToDisappear();

        Assert.assertEquals(signUpPage.getPasswordError(),"Must contain at least one uppercase, one lowercase, one number and one special character");

        signUpPage.clearPassword()
                .enterPassword("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890Test!!")
                .waitForPasswordErrorToDisappear()
                .enterPassword("Q");

        Assert.assertEquals(signUpPage.getPasswordError(),"must be smaller than 257");
    }

    @Test
    public void logInButtonTest(){
        driver.get("https://dev.datalexing.sa/en/register/");
        new SignUpPage(driver)
                .clickLogInLinkButton();
        SeleniumHelper.waitForElementToBeClickable(driver, By.xpath("//a[text()='Forgot password']"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://dev.datalexing.sa/en/login/");
    }
}
