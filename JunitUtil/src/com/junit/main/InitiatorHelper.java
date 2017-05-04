package com.junit.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.junit.file.FileOperator;
import com.junit.file.WriteToExcel;
import com.junit.mq.MQConnector;
import com.junit.pojo.Environment;
import com.junit.pojo.ReportAttributes;
import com.junit.util.PropertiesLoader;

public class InitiatorHelper {

	public void executeMQRequest(String configFileLocation,String requestLocation,String expectedResultLocation) throws InterruptedException{
		/*Initializing the required variables*/
		String requestFileName ;
		String expectedFilePath;
		String testStatus;
		String strException;
		
		String csvPath = expectedResultLocation +"JUnit_Report.xls"; // Path to generate the test report
		
		ArrayList<ReportAttributes> reportContent = new ArrayList<ReportAttributes>();
		WriteToExcel writeToExcel = new WriteToExcel();
		ReportAttributes reportAttr;

		// step 1: connect to MQ with the properties of the config file
		MQConnector mqConnector = MQConnector.getInstance(configFileLocation);
		//step 2: read the request message
		FileOperator fileOperator = new FileOperator(); 
		final File requestfolder = new File(requestLocation);
		
		 // For loop for request files for MQ flows
		 for (final File fileEntry : requestfolder.listFiles()) {
			 
			   expectedFilePath=null;
			   reportAttr = new ReportAttributes();
			   
		        if (fileEntry.isDirectory()) {
		            listFilesForFolder(fileEntry);
		        } else {
		        	
		            requestFileName = fileEntry.getName();
		            expectedFilePath = expectedResultLocation + "Expected_" + requestFileName;
		           
		            String request_message = fileOperator.fileReader(fileEntry.getPath());
		       
		            System.out.println("Message is" + request_message);
		         // step 3: Put the message on the input queue which is mentioned in config file
		    		
		    		byte[]  messageId = mqConnector.putMessage(request_message);
		    		System.out.println("Request sent to the flow, Please wait for the output..." +request_message);
		    		Thread.sleep(5000);
		    		
		    		// Step 4: read the response from the output queue
		    		String actual_result = mqConnector.getMessage(messageId);
		    		System.out.println("Actual Result :: " + actual_result);
		    		String expected_result = fileOperator.fileReader(expectedFilePath);
		    		System.out.println("Expected Result :: " + expected_result);
		    		//step 4: compare the actual result and the expected result
		    		
		    		try{
		    			fileOperator.compareResult(expected_result, actual_result);
		    			testStatus = "Success";
		    			strException = "";
		    		} catch(AssertionError ae){
		    			System.out.println("FAILURE : Actual and Expected results Mismatch" + ae);
		    			testStatus = "Failure";
		    			strException = ae.getMessage();
		    		}
		    		reportAttr.setFileName(requestFileName);
		    		reportAttr.setActualOutput(actual_result);
		    		reportAttr.setStatus(testStatus);
		    		reportAttr.setException(strException);
		    		reportContent.add(reportAttr);
		        }
		    } 
		 writeToExcel.createExcel(csvPath, reportContent);
	}
	
	public void executeSOAPRequest(String configFileLocation,String requestLocation,String expectedResultLocation){
		/*Initializing the required variables*/
		String requestFileName ;
		String expectedFilePath;
		String testStatus;
		String strException;
		
		String csvPath = expectedResultLocation +"JUnit_Report.xls"; // Path to generate the test report
		
		ArrayList<ReportAttributes> reportContent = new ArrayList<ReportAttributes>();
		WriteToExcel writeToExcel = new WriteToExcel();
		ReportAttributes reportAttr;
		
		//step 1: Get the SOAP URL to hit
		Environment environment;
	    PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
		environment = propertiesLoader.loadProperties(configFileLocation);
		String soapURL = environment.getUrl();
		String clientId = environment.getClientId();
		
		
		SOAPConnectionFactory soapConnectionFactory;
		SOAPConnection soapConnection;
		try {
			//Step 2: Create SOAP Connection
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			
			//step 3: read the request message
			FileOperator fileOperator = new FileOperator(); 
			final File requestfolder = new File(requestLocation);

			// For loop for request files for webservice flows
			for (final File fileEntry : requestfolder.listFiles()) {
			
				expectedFilePath=null;
				reportAttr = new ReportAttributes();
			
				if (fileEntry.isDirectory()) {
					listFilesForFolder(fileEntry);
				} else {
					
					requestFileName = fileEntry.getName();
					expectedFilePath = expectedResultLocation + "Expected_" + requestFileName;
       
					String request_message = fileOperator.fileReader(fileEntry.getPath());
				
					
					
					request_message.addHeader("X-IBM-Client-Id",clientId);
					//convert request msg to soap message
					InputStream is = new ByteArrayInputStream(request_message.getBytes());
					SOAPMessage request = MessageFactory.newInstance().createMessage(null,is);
 
					// Send SOAP Message to SOAP Server
					SOAPMessage soapResponse = soapConnection.call(request, soapURL);
					System.out.println("Request sent to the flow, Please wait for the output..." +request_message);
					Thread.sleep(5000);
				
					//process soap response
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					soapResponse.writeTo(out);
					String actual_result = new String(out.toByteArray());
	
					System.out.println("Actual Result :: " + actual_result);
					String expected_result = fileOperator.fileReader(expectedFilePath);
					System.out.println("Expected Result :: " + expected_result);
					
					//step 4: compare the actual result and the expected result
					try{
						fileOperator.compareResult(expected_result, actual_result);
						testStatus = "Success";
		    			strException = "";
					} catch(AssertionError ae){
						System.out.println("FAILURE : Actual and Expected results Mismatch" + ae);
						testStatus = "Failure";
		    			strException = ae.getMessage();
					}
					reportAttr.setFileName(requestFileName);
	    			reportAttr.setActualOutput(actual_result);
	    			reportAttr.setStatus(testStatus);
	    			reportAttr.setException(strException);
	    			reportContent.add(reportAttr);
				}
			} 
			writeToExcel.createExcel(csvPath, reportContent);
		} catch (UnsupportedOperationException | SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException | IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}

}
