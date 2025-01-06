package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {

    public static String loadProperty(String propertyName) throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/config.properties"));
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty(propertyName);

    }


}
