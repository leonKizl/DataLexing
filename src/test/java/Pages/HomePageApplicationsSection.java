package Pages;

import Utils.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageApplicationsSection {

    @FindBy(id = "add-application-button")
    private WebElement addApplicationButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HomePageApplicationsSection(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public AddNewApplicationPage clickAddApplicationButton(){
        logger.info("Clicking add application button");
        SeleniumHelper.waitForElementToBeVisible(driver,addApplicationButton);
        addApplicationButton.click();
        logger.info("Button is clicked");
        return new AddNewApplicationPage(driver);
    }
}
