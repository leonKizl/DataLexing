package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {
    @FindBy(css = "[aria-label='Log in']")
    private WebElement logInButton;

    @FindBy(id = "email-input-text-error")
    private WebElement emailInputError;

    @FindBy(id = "password-input-text-error")
    private WebElement passwordInputError;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "a[href*='/forgot-password']")
    private WebElement forgotPasswordButton;

    @FindBy(css = "a[href*='/register']")
    private WebElement signUpButton;

    @FindBy(css = "span.text-sm-normal.col-span-3.text-zinc-700.Toast_message__tBNib")
    private WebElement popUpError;

    @FindBy(css = ".text-zinc-700 > div > svg")
    private WebElement closePopUpErrorButton;

    private static final Logger logger = LogManager.getLogger();

    private WebDriver driver;



    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void enterEmail(String email) {
        SeleniumHelper.waitForElementToBeVisible(driver,emailInput);
        logger.info("Entering email");
        emailInput.sendKeys(email);
        logger.info("Email is entered");
    }

    public void clearEmail() {
        logger.info("Clearing email input");
        emailInput.clear();
        logger.info("Email input is empty");
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        SeleniumHelper.waitForElementToBeVisible(driver,passwordInput);
        passwordInput.sendKeys(password);
        logger.info("Password is entered");
    }

    public void clearPassword() {
        logger.info("Clearing password input");
        passwordInput.clear();
        logger.info("Password input is empty");
    }

    public String getEmailErrorInputText() {
        logger.info("Getting text from EmailInputError");
        return emailInputError.getText();
    }

    public String getPasswordErrorInputText() {
        logger.info("Getting text from PasswordInputError");
        return passwordInputError.getText();
    }

    public void logInButtonClick() {
        logger.info("Clicking log in button");
        SeleniumHelper.waitForElementToBeVisible(driver,logInButton);
        logInButton.click();
        logger.info("Button is clicked");
    }

    public void absenceOfEmailInputError(WebDriver driver) {
        logger.info("Check absence of email input error");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.invisibilityOf(emailInputError));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new AssertionError("Email input error is displayed, but it should not be.");
        }
        logger.info("Absence of email input error is checked");
    }

    public void absenceOfPasswordInputError(WebDriver driver) {
        logger.info("Check absence of password input error");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.invisibilityOf(passwordInputError));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new AssertionError("Password input error is displayed, but it should not be.");
        }
        logger.info("Absence of password input error is checked");

    }

    public String getTextOfPopUpError() {
        SeleniumHelper.waitForElementToBeVisible(driver,popUpError);
        logger.info("Getting text from pop up");
        System.out.println(popUpError.getAttribute("textContent"));
        return popUpError.getAttribute("textContent");
    }

    public void closePopUpError(WebDriver driver){
        logger.info("Closing pop up");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(closePopUpErrorButton));
        actions.moveToElement(closePopUpErrorButton).click().perform();
        logger.info("pop up is closed");
    }

    public void absenceOfPopUpError() throws InterruptedException {
        logger.info("Check if pop up doesn't display");
        Thread.sleep(1000);
        try {
            // Проверяем видимость popUpError
            if (popUpError.isDisplayed()) {
                throw new AssertionError("Pop-up error should not be displayed.");
            }
        } catch (NoSuchElementException e) {
            // Если элемент отсутствует, ничего не делаем
        }
        logger.info("pop up displaying is checked");
    }
}
