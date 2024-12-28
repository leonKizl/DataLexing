package Pages;

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

    @FindBy(id = "email-input")
    private WebElement emailInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(css = "a[href*='/forgot-password']")
    private WebElement forgotPasswordButton;

    @FindBy(css = "a[href*='/register']")
    private WebElement signUpButton;

    @FindBy(css = "span.text-sm-normal.col-span-3.text-zinc-700.Toast_message__tBNib")
    private WebElement popUpError;

    @FindBy(css = ".text-zinc-700 > div > svg")
    private WebElement closePopUpErrorButton;


    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void clearEmail() {
        emailInput.clear();
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clearPassword() {
        passwordInput.clear();
    }

    public String getEmailErrorInputText() {
        return emailInputError.getText();
    }

    public String getPasswordErrorInputText() {
        return passwordInputError.getText();
    }

    public void logInButtonClick() {
        logInButton.click();
    }

    public void absenceOfEmailInputError(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.invisibilityOf(emailInputError));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new AssertionError("Email input error is displayed, but it should not be.");
        }
    }

    public void absenceOfPasswordInputError(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        try {
            wait.until(ExpectedConditions.invisibilityOf(passwordInputError));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new AssertionError("Password input error is displayed, but it should not be.");
        }

    }

    public String getTextOfPopUpError() {
        System.out.println(popUpError.getAttribute("textContent"));
        return popUpError.getAttribute("textContent");
    }

    public void closePopUpError(WebDriver driver){
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(closePopUpErrorButton));
        actions.moveToElement(closePopUpErrorButton).click().perform();
    }

    public void absenceOfPopUpError() throws InterruptedException {
        Thread.sleep(1000);
        try {
            // Проверяем видимость popUpError
            if (popUpError.isDisplayed()) {
                throw new AssertionError("Pop-up error should not be displayed.");
            }
        } catch (NoSuchElementException e) {
            // Если элемент отсутствует, ничего не делаем
        }
    }
}
