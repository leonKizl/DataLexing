package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LogInPage {

    @Test
    public void SmokeLoginTest(){
        WebDriver driver = WebDriverSelector.getDriver("chrome");
        driver.get("https://dev.datalexing.sa/en/login/");
        driver.quit();
    }
}
