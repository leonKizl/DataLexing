package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriver driver = DriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.driver = driver;
    }
    @AfterMethod
    public void tearsDown(){
        driver.quit();
    }
}
