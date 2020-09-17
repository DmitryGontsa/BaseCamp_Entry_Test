package common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

    private static final String PROPERTY_FILE_PATH = "./src/main/resources/properties/environment.properties";
    private Properties properties;

    public PropertyHelper() {
        properties = new Properties();
        try {
            properties.load(new FileReader(new File(PROPERTY_FILE_PATH)));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read properties file", e);
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
