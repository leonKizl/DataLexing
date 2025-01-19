package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewApplicationPage {

    @FindBy(xpath = "//button[contains(text(), 'Add')]")
    private WebElement addNewApplicationButton;

    @FindBy(xpath = "//button[contains(text(), 'Cancel')]")
    private WebElement cancelButton;

    @FindBy(name = "applicationName")
    private WebElement applicationNameInput;

    @FindBy(xpath = "//*[contains(@id,'-text-error')]")
    private WebElement appNameError;

    @FindBy(xpath = "//button[contains(@value, 'From template')]")
    private WebElement fromTemplateRadioButton;

    @FindBy(xpath = "//button[contains(@value, ' From scratch')]")
    private WebElement fromScratchRadioButton;

    @FindBy(css = "div.react-select__indicators.css-1wy0on6")
    private WebElement workspaceDropdown;

    @FindBy(css = "div.sc-eWPXlR.hxfQZm use")
    private WebElement addArabicTranslationButton;

    @FindBy(xpath = "//div[contains(@class,'sc-jIyAiq cXDlaC')]//button")
    private WebElement iconSelectorButton;

    @FindBy(xpath = "//h2[contains(text(),'Add new Application')]")
    private WebElement titleAddNewApp;

    @FindBy(xpath = "//div[@aria-label = 'Close']")
    private WebElement closeWindowButton;
    //change it
    @FindBy(xpath = "(//div[@id='new-sidebar-app']//div[contains(text(), 'createdAppp')])[1]")
    private WebElement createdAppInWorkspace;

    private WebDriver driver;

    private final static Logger logger = LogManager.getLogger();

    public AddNewApplicationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public AddNewApplicationPage enterApplicationName(String appsName){
        SeleniumHelper.waitForElementToBeVisible(driver,applicationNameInput);
        logger.info("Entering appps name");
        applicationNameInput.sendKeys(appsName);
        logger.info("Name is entered");
        return this;
    }

    public HomePage clickAddButton(){
        logger.info("Clicking 'Add' button");
        addNewApplicationButton.click();
        logger.info("Button is clicked");
        return new HomePage(driver);
    }

    public HomePage clickCancelButton(){
        logger.info("Clicking 'Cancel' button");
        cancelButton.click();
        logger.info("Button is clicked");
        return new HomePage(driver);
    }

}
