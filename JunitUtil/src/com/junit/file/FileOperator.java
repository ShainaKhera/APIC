package com.junit.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.xml.sax.SAXException;

import com.junit.mq.MQConnector;

/**
 * @author jaya.c.atmakuri
 *
 */
public class FileOperator{
	
	public void fileWriter(String message) throws IOException{
		 BufferedWriter bufferedWriter = null;
		 File jarPath=new File(MQConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		 String propertiesPath=jarPath.getParentFile().getAbsolutePath();
         try {
        	 String file = propertiesPath + "/ActualResult.xml";
             File myFile = new File(file);
             // check if file exist, otherwise create the file before writing
             if (!myFile.exists()) {
                 myFile.createNewFile();
             }
             Writer writer = new FileWriter(myFile);
             bufferedWriter = new BufferedWriter(writer);
             bufferedWriter.write(message);
         } catch (IOException e) {
             e.printStackTrace();
         } finally{
         	if(bufferedWriter != null) bufferedWriter.close();
         }
	}
	
	 @SuppressWarnings("finally")
		public String fileReader(String fileName) {
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
	
		public void compareResult(String expected, String actual) {
			int diffCnt = 0;
			try {
				 DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expected, actual));
				 List<?> allDifferences = diff.getAllDifferences();
				 diffCnt = allDifferences.size();
			     Assert.assertEquals("Differences found: "+ diff.toString(), 0, allDifferences.size());
			     System.out.println("SUCCESS : Actual and Expected results Matched");		
			}catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void generatetestCSV ( String fileName, ArrayList content )
		{
		    try
		    {
		    	System.out.println("Generate Report");
		        FileWriter writer = new FileWriter( fileName );
		        writer.append( "RequestFile" );
		        writer.append( ',' );
		        writer.append( "Actual Output" );
		        writer.append( ',' );
		        writer.append( "Result" );
		        writer.append( ',' );
		        writer.append( "Exception" );
		        writer.append( '\n' );
		        Iterator<String> iterContent = content.iterator();
		        while (iterContent.hasNext()){
		        	String lstContent = iterContent.next();
		        	System.out.println("Content = "+ lstContent);
		        	writer.append(lstContent);
		        	writer.append( '\n' );
		        }
		        writer.flush();
		        writer.close();
		    }
		    catch ( IOException e )
		    {
		        e.printStackTrace();
		    }
		}
}