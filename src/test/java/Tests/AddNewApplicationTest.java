package Tests;

import Pages.HomePageApplicationsSection;
import Pages.LogInPage;
import Utils.SeleniumHelper;
import org.example.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AddNewApplicationTest extends BaseTest {

    @Test
    public void CreateAppTest(){
        driver.get("https://dev.datalexing.sa/en/login/");
        new LogInPage(driver)
                .validLogIn();
        new HomePageApplicationsSection(driver)
                .clickAddApplicationButton()
                .enterApplicationName("autoName")
                .clickAddButton();
        SeleniumHelper.waitForElementToExist(driver, By.xpath("(//div[@id='new-sidebar-app']//div[contains(text(), 'autoName')])[1]"));
    }
}
