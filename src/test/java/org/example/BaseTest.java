package org.example;

import Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        WebDriver driver = DriverFactory.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.driver = driver;
    }
    @AfterMethod
    public void tearsDown(){
        driver.quit();
    }
}
