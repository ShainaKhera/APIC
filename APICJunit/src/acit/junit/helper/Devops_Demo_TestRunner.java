package acit.junit.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.*;
import java.io.*;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.xml.sax.SAXException;


//import acit.junit.helper.Devops_Demo_TestHelper;
//import acit.junit.test.Devops_Demo_TestCases;

public class Devops_Demo_TestRunner {

	@Test
	public void test1() throws Exception{
		String workDir =System.getProperty("user.dir"); 
		int i = 1;			
	    // Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://10.109.95.154/calculator";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i), url);
        
		//System.out.print("Response SOAP Message = ");
		//soapResponse.writeTo(System.out);
        
        //System.out.println();
        
		// Process the SOAP Response
     
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
   	      
        StreamResult result = new StreamResult(new File(workDir+ "//actual//actualoutput" +Integer.toString(i)+ ".xml"));
        transformer.transform(sourceContent, result);
		
		soapConnection.close();
		testPrintMessage(i);			
	}
	
	@Test
	public void test2() throws Exception{
		String workDir =System.getProperty("user.dir"); 
		int i = 2;			
	    // Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://10.109.95.154/calculator";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i), url);
        
		//System.out.print("Response SOAP Message = ");
		//soapResponse.writeTo(System.out);
        
        //System.out.println();
        
		// Process the SOAP Response
     
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
   	      
        StreamResult result = new StreamResult(new File(workDir+ "//actual//actualoutput" +Integer.toString(i)+ ".xml"));
        transformer.transform(sourceContent, result);
		
		soapConnection.close();
		testPrintMessage(i);			
	}
	
	@Test
	public void test3() throws Exception{
		String workDir =System.getProperty("user.dir"); 
		int i = 3;			
	    // Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://10.109.95.154/calculator";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i), url);
        
		//System.out.print("Response SOAP Message = ");
		//soapResponse.writeTo(System.out);
        
        //System.out.println();
        
		// Process the SOAP Response
     
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
   	      
        StreamResult result = new StreamResult(new File(workDir+ "//actual//actualoutput" +Integer.toString(i)+ ".xml"));
        transformer.transform(sourceContent, result);
		
		soapConnection.close();
		testPrintMessage(i);			
	}
	@Test
	public void test4() throws Exception{
		String workDir =System.getProperty("user.dir"); 
		int i = 4;			
	    // Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://10.109.95.154/calculator";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i), url);
        
		//System.out.print("Response SOAP Message = ");
		//soapResponse.writeTo(System.out);
        
        //System.out.println();
        
		// Process the SOAP Response
     
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
   	      
        StreamResult result = new StreamResult(new File(workDir+ "//actual//actualoutput" +Integer.toString(i)+ ".xml"));
        transformer.transform(sourceContent, result);
		
		soapConnection.close();
		testPrintMessage(i);			
	}
	
	@Test
	public void test5() throws Exception{
		String workDir =System.getProperty("user.dir"); 
		int i = 5;			
	    // Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://10.109.95.154/calculator";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i), url);
        
		//System.out.print("Response SOAP Message = ");
		//soapResponse.writeTo(System.out);
        
        //System.out.println();
        
		// Process the SOAP Response
     
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
   	      
        StreamResult result = new StreamResult(new File(workDir+ "//actual//actualoutput" +Integer.toString(i)+ ".xml"));
        transformer.transform(sourceContent, result);
		
		soapConnection.close();
		testPrintMessage(i);			
	}
    @SuppressWarnings("finally")
	public String readFileContent(String fileName) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return sb.toString();
		}
	}
    
	public SOAPMessage createSOAPRequest(int i)throws Exception {     	
    	String workDir =System.getProperty("user.dir"); 
    	String serverURI = "http://www.webserviceX.NET";   
    	String Request = "RequestMessage"+(Integer.toString(i))+ ".xml";
    	String message = readFileContent(workDir+ "//RequestMessages//" +Request); 
    	MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage soapMessage = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(message.getBytes(Charset.forName("UTF-8"))));
        
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction",serverURI + "/GetCitiesByCountry");

        soapMessage.saveChanges();
        /* Print the request message */
       // System.out.print("Request SOAP Message = ");
        //soapMessage.writeTo(System.out);
        
        //System.out.println();

        return soapMessage;
    }

    @SuppressWarnings("finally")
	public void testPrintMessage(int i) {
		try {

			//Result result;
			//JUnitCore.runClasses(Devops_Demo_TestCases.class);	
			String workDir =System.getProperty("user.dir"); 
			String expected = "expectedoutput"+(Integer.toString(i))+ ".xml";
			String actual = "actualoutput"+(Integer.toString(i))+ ".xml";

			String message = readFileContent(workDir+ "//expected//" + expected); 
		    String message1 = readFileContent(workDir+ "//actual//" +actual);

			XMLAssert.assertXMLEqual(message, message1);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
  
}