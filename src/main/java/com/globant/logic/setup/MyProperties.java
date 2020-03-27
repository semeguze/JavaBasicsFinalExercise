package com.globant.logic.setup;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

/**
 * This class manage the logic to interact with the properties files
 */
@Slf4j
public class MyProperties {

    /**
     * Allow to manage the logic to connect to the properties file
     * @return the Property object with the settings properties
     */
    public Properties getProperties() {
        try {
            Properties property = new Properties();
            InputStream input = this.getClass().getResourceAsStream("/settings.properties");
            property.load(input);
            return property;
        } catch (Exception e) {
            log.error("Error loading properties file. Search for 'settings.properties'");
        }
        return null;
    }
}
