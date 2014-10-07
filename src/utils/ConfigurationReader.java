package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * This will load any property file under config/{env}/file.properties.
 * Currently this implementation loads only one file at a time.
 */
public class ConfigurationReader {

    private PropertiesConfiguration propertiesConfiguration;

    public ConfigurationReader(String configFile) {
        try {
            propertiesConfiguration = new PropertiesConfiguration(getClass().getResource(resource(configFile)).getFile());
        } catch (ConfigurationException e) {
            throw new RuntimeException("Error loading config file ", e);
        }
    }

    private String resolveEnvironment() {
        return System.getProperty("env", "dev");
    }

    private String resource(String configFile) {
        return "/config/" + resolveEnvironment() + "/" + configFile;
    }

    public String valueNamedString(String key) {
        return propertiesConfiguration.getString(key);
    }

    public Long valueNamedLong(String key) {
        return propertiesConfiguration.getLong(key);
    }
}
