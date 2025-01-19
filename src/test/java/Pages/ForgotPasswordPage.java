package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @FindBy(css = "[aria-label='Log in']")
    private WebElement logInLinkButton;

    @FindBy(css = "[aria-label*='Recover']")
    private WebElement recoverPasswordButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public LogInPage clickLogInLinkButton(){
        logger.info("Clicking Log In link button");
        SeleniumHelper.waitForElementToBeVisible(driver,recoverPasswordButton);
        logInLinkButton.click();
        logger.info("Log in button is clicked");
        return new LogInPage(driver);
    }

    public ForgotPasswordPage enterEmail(String email){
        logger.info("Entering email");
        SeleniumHelper.waitForElementToBeVisible(driver,emailInput);
        emailInput.sendKeys(email);
        logger.info("Email is entered");
        return this;
    }

    public ForgotPasswordPage clickRecoverPasswordButton(){
        logger.info("Clicking recover password button");
        recoverPasswordButton.click();
        logger.info("Button is clicked");
        return this;
    }

}
