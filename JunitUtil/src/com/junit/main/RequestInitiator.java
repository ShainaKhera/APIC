/**
 * The main class from where the execution gets started. 
 * This class route the request to appropriate requests based on the PROTOCOL
 */

package com.junit.main;
import com.junit.pojo.Environment;
import com.junit.util.PropertiesLoader;

/**
 * @author jaya.c.atmakuri
 *
 */
public class RequestInitiator {
public static void main(String[] args) {

   /*Get the current working directory and create the path for request and expected output files*/
	String workDir =System.getProperty("user.dir");
		startProcess(workDir + "\\TestFiles\\config\\config.properties",
					 workDir + "\\TestFiles\\Request\\", 
					 workDir + "\\TestFiles\\Expected\\");
}

/* 
 * Method name: startProcess
 * Description : method takes 3 parameters as input which are defined as follow:
 * configFileLocation : location of the config file where the connection details are mentioned for mq
 * requestLocation : location of the request, which is request for the service that we want to test.
 * expectedResultLocation : expected result of the service for which we sent the request
 * 
 */
	public static void startProcess(String configFileLocation,String requestLocation,String expectedResultLocation){
		try{
			// to get protocol value
			Environment environment;
			PropertiesLoader propertiesLoader;
			propertiesLoader = PropertiesLoader.getInstance();
			environment = propertiesLoader.loadProperties(configFileLocation);
			String strProtocol = environment.getProtocol();
			System.out.println("protocol=" + strProtocol);
			
			InitiatorHelper helper = new InitiatorHelper();
			
			switch(strProtocol) {
				case "MQ" : helper.executeMQRequest(configFileLocation, requestLocation, expectedResultLocation);
		
							break;
										
				case "SOAP" : helper.executeSOAPRequest(configFileLocation, requestLocation, expectedResultLocation);
	
							break;
				default: System.out.println("UnExpected Protocol");
			}			
		}catch (Exception e) {
			System.out.println("Exception in startProcess : " +e.getMessage());
		}	
	}
}
