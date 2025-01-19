package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//p[contains(text(),'Homepage')]")
    private WebElement homePageButton;

    @FindBy(xpath = "//p[contains(text(),'Analytics')]")
    private WebElement analyticsButton;

    @FindBy(xpath = "//p[contains(text(),'AI Assistant')]")
    private WebElement aiAssistantButton;

    @FindBy(xpath = "//p[contains(text(),'Notifications')]")
    private WebElement notificationsButton;

    @FindBy(xpath = "//p[contains(text(),'Plan and Licensing')]")
    private WebElement planAndLicensingButton;

    @FindBy(xpath = "//p[contains(text(),'Help')]")
    private WebElement helpButton;

    @FindBy(xpath = "//p[contains(text(),'Automations')]")
    private WebElement automationsButton;

    @FindBy(xpath = "//p[contains(text(),'Trash')]")
    private WebElement trashButton;

    @FindBy(css = "div[aria-label='user menu toggle']")
    private WebElement userMenuToggle;

    @FindBy(xpath = "//*[contains(text(), 'show again')]")
    private WebElement dontShowAgainButton;

    @FindBy(xpath = "//*[contains(text(), 'Log out')]")
    private WebElement logOutButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public HomePage clickUserMenuToggle(){
        logger.info("Clicking on user menu toggle");
        SeleniumHelper.waitForElementToBeVisible(driver,userMenuToggle);
        userMenuToggle.click();
        logger.info("Toggle is clicked");
        return this;
    }

    public HomePage clickDontShowAgainButton(){
        logger.info("Clicking 'dont show again button' on pnboarding window");
        SeleniumHelper.waitForElementToBeVisible(driver,dontShowAgainButton);
        dontShowAgainButton.click();
        logger.info("Button is clicked");
        return this;
    }

    public LogInPage clickLogOutButton(){
        logger.info("Clicking log out button");
        logOutButton.click();
        logger.info("Button is clicked");
        return new LogInPage(driver);
    }


}
