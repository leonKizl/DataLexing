package Pages;

import Utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ForgotPasswordPage {

    @FindBy(name = "email")
    private WebElement emailInput;

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void getTemporaryEmail() {
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
        System.out.println(temporaryEmail);
    }
}
