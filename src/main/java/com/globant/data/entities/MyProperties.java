package com.globant.data.entities;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MyProperties {

    public Properties getProperties() {
        try {
            Properties property = new Properties();
            InputStream input = this.getClass().getResourceAsStream("/settings.properties");
            property.load(input);
            return property;
        }catch (Exception e){
            log.error("Error loading properties file. Search for 'settings.properties'");
        }
        return null;
    }
}
