/**
 * 
 */
package com.junit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.junit.pojo.Environment;

/**
 * @author jaya.c.atmakuri
 *
 */
public class PropertiesLoader {
	private static PropertiesLoader propertiesLoader;
	
	/**
     * Create private constructor
     */
    private PropertiesLoader(){
         
    }
    
    /**
     * Create a static method to get instance.
     */
    public static PropertiesLoader getInstance(){
        if(propertiesLoader == null){
        	propertiesLoader = new PropertiesLoader();
        }
        return propertiesLoader;
    }
    
	public Environment loadProperties(String configFileLocation){
		Environment environment = new Environment(); 
		InputStream inputStream;
		Properties prop = new Properties();
		/*File jarPath=new File(MQConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	    String propertiesPath=jarPath.getParentFile().getAbsolutePath();
	    System.out.println(" propertiesPath - " + propertiesPath);*/
		try {
			//inputStream = new FileInputStream(propertiesPath + "/config.properties");
			//inputStream = new FileInputStream("C:\\Jaya\\Junit\\Testing\\config\\config.properties");
			inputStream = new FileInputStream(configFileLocation);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			  System.out.println("java.junit.util.PropertiesLoader FileNotFoundException : " + e.getMessage());
			 // System.out.println("property file '" + propertiesPath + "/config.properties' not found in the classpath");
		}catch (IOException e) {
			 System.out.println("java.junit.util.PropertiesLoader IOException : " + e.getMessage());
		}
		environment.setHostName(prop.getProperty("hostName"));
		environment.setQueuemanager(prop.getProperty("queuemanager"));
		environment.setChannel(prop.getProperty("channel"));
		environment.setPort(prop.getProperty("port"));
		environment.setUser(prop.getProperty("user"));
		environment.setPassword(prop.getProperty("password"));
		environment.setIn_queue(prop.getProperty("in_queue"));
		environment.setOut_queue(prop.getProperty("out_queue"));
		environment.setMessage(prop.getProperty("message"));
		environment.setUrl(prop.getProperty("url"));
		environment.setProtocol(prop.getProperty("protocol"));
		environment.setClientId(prop.getProperty("clientId"));
		return environment;
	}
}
