package Tests;

import Pages.LogInPage;
import Utils.SeleniumHelper;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.Set;

public class ForgotPasswordTest extends BaseTest {


    @Test
    public void forgotPasswordTest() {
        driver.get("https://dev.datalexing.sa/en/login/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");
        String mainWindow = driver.getWindowHandle();
        String windowWithTempMail = null;
        Set<String> allWindowsHandles = driver.getWindowHandles();
        for (String el : allWindowsHandles) {
            if (!el.equals(mainWindow)) {
                windowWithTempMail = el;
            }
        }
        driver.switchTo().window(windowWithTempMail);
        driver.get("https://temp-mail.org/");
        SeleniumHelper.waitForElementToExist(driver, By.xpath("//input[@class='emailbox-input opentip']"));
        String temporaryEmail = driver.findElement(By.id("mail")).getAttribute("value");
        driver.switchTo().window(mainWindow);
        new LogInPage(driver)
                .clickForgotPasswordButton()
                .clickLogInLinkButton()
                .clicksignUpButton()
                .validSignUp(temporaryEmail)
                .clickDontShowAgainButton()
                .clickUserMenuToggle()
                .clickLogOutButton()
                .clickForgotPasswordButton()
                .enterEmail(temporaryEmail)
                .clickRecoverPasswordButton();
        driver.switchTo().window(windowWithTempMail);
        SeleniumHelper.waitForElementToBeClickable(driver,By.xpath("//a[contains(text(),'Reset password - Datalexing')]"));




    }
}
