package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {

    @FindBy(linkText = "Log in")
    private WebElement logInLinkButton;

    @FindBy(xpath = "//div[contains(text(), 'Create a profile')]")
    private WebElement createProfileTitle;

    @FindBy(name = "name")
    private WebElement fullNameInput;

    @FindBy(name = "email")
    private  WebElement emailInput;

    @FindBy(name = "password")
    private  WebElement passwordInput;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberInput;

    @FindBy(id = "terms-privacy-checkbox")
    private WebElement termsPrivacyCheckbox;

    @FindBy(css = "button[aria-label = 'Sign up']")
    private WebElement signUpButton;

    @FindBy(id = "full-name-input-text-error")
    private WebElement fullNameError;

    @FindBy(id = "email-input-text-error")
    private WebElement emailError;

    @FindBy(id = "password-input-text-error")
    private WebElement passwordError;

    @FindBy(xpath = "//span[contains(@id,'input-17')]")
    private WebElement phoneNumberError;

    @FindBy(css = "span.text-xs-normal.mt-1.text-red-600")
    private WebElement termsPrivacyCheckboxError;

    @FindBy(css = "div.flag-dropdown")
    private WebElement flagDropdown;



    private static final Logger logger = LogManager.getLogger();

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getTitleText(){
        logger.info("Getting title");
        SeleniumHelper.waitForElementToBeVisible(driver,createProfileTitle);
        logger.info("Title is visible");
        return createProfileTitle.getText();

    }

    public SignUpPage enterFullName(String fullName){
        logger.info("Entering full Name");
        SeleniumHelper.waitForElementToBeVisible(driver,fullNameInput);
        fullNameInput.sendKeys(fullName);
        logger.info("Full name is entered");
        return this;
    }

    public SignUpPage clearFullName(){
        logger.info("Deleting data from full name input");
        fullNameInput.clear();
        logger.info("Data is deleted");
        return this;
    }

    public SignUpPage enterEmail(String email){
        logger.info("Entering email");
        emailInput.sendKeys(email);
        logger.info("Email is entered");
        return this;
    }

    public SignUpPage clearEmail(){
        logger.info("Deleting data from email input");
        emailError.clear();
        logger.info("Data is deleted");
        return this;
    }

    public SignUpPage enterPassword(String password){
        logger.info("Entering password");
        passwordInput.sendKeys(password);
        logger.info("Password is entered");
        return this;
    }

    public SignUpPage clearPassword(){
        logger.info("Deleting data from password input");
        passwordInput.clear();
        logger.info("Data is deleted");
        return this;
    }

    public SignUpPage enterPhoneNumber(String phoneNumber){
        logger.info("Entering phone number");
        phoneNumberInput.sendKeys(phoneNumber);
        logger.info("Phone number is entered");
        return this;
    }

    public void clickTermPrivacyButton(){
        logger.info("Clicking checkbox terms and privacy");
        SeleniumHelper.waitForElementToBeClickable(driver,By.id("terms-privacy-checkbox"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", termsPrivacyCheckbox);
        logger.info("Checkbox is clicked");
    }

    public void clickSignUpButton(){
        logger.info("Clicking sign up button");
        SeleniumHelper.waitForElementToBeVisible(driver,signUpButton);
        signUpButton.click();
        logger.info("Button is clicked");
    }

    public String getFullNameError(){
        logger.info("Getting error message from Full Name field");
        return fullNameError.getText();
    }

    public String getEmailError(){
        logger.info("Getting error message from email field");
        return emailError.getText();
    }

    public String getPasswordError(){
        logger.info("Getting error message from password field");
        return passwordError.getText();
    }

    public String getPhoneNumberError(){
        logger.info("Getting error message from phone number field");
        SeleniumHelper.waitForElementToBeVisible(driver,phoneNumberError);
        return phoneNumberError.getText();
    }

    public String getCheckBoxError(){
        logger.info("Getting error message from checkbox");
        return termsPrivacyCheckboxError.getText();
    }

    public SignUpPage waitForFullNameErrorToDisappear(){
        logger.info("Checking if fullname error validation disappears");
        SeleniumHelper.waitForElementToBeInvisible(driver,fullNameError);
        logger.info("Absence of error");
        return this;
    }

    public SignUpPage waitForEmailErrorToDisappear(){
        logger.info("Checking if email error validation disappears");
        SeleniumHelper.waitForElementToBeInvisible(driver,emailError);
        logger.info("Absence of error");
        return this;
    }

    public SignUpPage waitForPasswordErrorToDisappear(){
        logger.info("Checking if password error validation disappears");
        SeleniumHelper.waitForElementToBeInvisible(driver,passwordError);
        logger.info("Absence of error");
        return this;
    }

    public SignUpPage waitForPhoneNumberErrorToDisappear(){
        logger.info("Checking if phoneNumber error validation disappears");
        SeleniumHelper.waitForElementToBeInvisible(driver,phoneNumberError);
        logger.info("Absence of error");
        return this;
    }

    public SignUpPage waitForTermsCheckBoxErrorToDisappear(){
        logger.info("Checking if checkbox error validation disappears");
        SeleniumHelper.waitForElementToBeInvisible(driver,termsPrivacyCheckboxError);
        logger.info("Absence of error");
        return this;
    }

    public void clickFlagDropdown(String countryName){
        logger.info("Clicking country dropdown in phone number field and selecting country");
        flagDropdown.click();
        WebElement countryOption = driver.findElement(By.xpath("//ul[contains(@class, 'country-list')]//span[contains(@class, 'country-name') and text()='"+countryName+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", countryOption);
        logger.info("Button clicked");
    }

    public LogInPage clickLogInLinkButton(){
        logger.info("Clicking log in link button");
        SeleniumHelper.waitForElementToBeVisible(driver,logInLinkButton);
        logInLinkButton.click();
        logger.info("Log in link button is clicked");
        return new LogInPage(driver);
    }

    public HomePage validSignUp(String email){
        SeleniumHelper.waitForElementToBeVisible(driver,fullNameInput);
        enterFullName("name");
        enterEmail(email);
        enterPassword("Testowanie999!!!");
        enterPhoneNumber("123456");
        clickTermPrivacyButton();
        clickSignUpButton();
        return new HomePage(driver);
    }
}
