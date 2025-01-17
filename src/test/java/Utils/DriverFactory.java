package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class DriverFactory {
    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");
        if (name.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            //Add location of your ChromeDriver on your computer and comment row with path from other computer //JSON WITH ALL VERSIONS (https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json)
            String LeonWindowsChromedriver = "C:\\\\Users\\\\leoni\\\\OneDrive\\\\Рабочий стол\\\\instalators\\\\drivers\\\\chromedriver-win64\\\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", LeonWindowsChromedriver);
            return new ChromeDriver(chromeOptions);
        } else if (name.equals("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            //Add location of your FirefoxDriver on your computer and comment row with path from other computer // https://github.com/mozilla/geckodriver/releases
            String LeonWindowsFirefoxDriver = "C:\\Users\\leoni\\OneDrive\\Рабочий стол\\instalators\\drivers\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", LeonWindowsFirefoxDriver);
            return new FirefoxDriver(firefoxOptions);
        } else if (name.equals("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            //Add location of your EdgeDriver on your computer and comment row with path from other computer // https://developer.microsoft.com/ru-ru/microsoft-edge/tools/webdriver/?form=MA13LH
            String LeonWindowsEdgeDriver = "C:\\Users\\leoni\\OneDrive\\Рабочий стол\\instalators\\drivers\\msedgedriver.exe";
            edgeOptions.addArguments("--disable-features=IsolateOrigins,site-per-process", "--remote-allow-origins=*");
            System.setProperty("webdriver.edge.driver", LeonWindowsEdgeDriver);
            return new EdgeDriver(edgeOptions);
        } else {
            throw new IllegalArgumentException("Unsupported driver: " + name);
        }

    }
}
