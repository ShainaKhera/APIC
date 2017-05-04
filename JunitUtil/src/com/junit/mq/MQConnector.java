/**
 * 
 */
package com.junit.mq;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.junit.pojo.Environment;
import com.junit.util.Base64;
import com.junit.util.PropertiesLoader;


/**
 * @author jaya.c.atmakuri
 *
 */
public class MQConnector {
	private static MQConnector mqConnector;
	private Environment environment;
	private PropertiesLoader propertiesLoader;
	MQQueue defaultInpQueue;
	MQQueue defaultOutQueue;
	private MQConnector(String configFileLocation){
		 propertiesLoader = PropertiesLoader.getInstance();
		 environment = propertiesLoader.loadProperties(configFileLocation);
		 this.getConnection();
	    }
	 public static MQConnector getInstance(String configFileLocation){
	        if(mqConnector == null){
	        	mqConnector = new MQConnector(configFileLocation);
	        }
	        return mqConnector;
	    } 
	 
//Get Connection to Queue manager
public MQQueueManager getConnection(){
	
	MQQueueManager queueManagerObj = null;
    //Set MQ connection details to MQ Environment.
    MQEnvironment.hostname = environment.getHostName();
    MQEnvironment.channel = environment.getChannel();
    MQEnvironment.port = Integer.parseInt(environment.getPort());
    String queuemanager = environment.getQueuemanager();
    MQEnvironment.userID = environment.getUser();
    /*Decoding the encoded password*/
    byte[] encodedPassword = environment.getPassword().getBytes();
    byte[]password = Base64.getDecoder().decode(encodedPassword);
    MQEnvironment.password = new String(password);
    
    String protocol = environment.getProtocol();
    MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES_CLIENT);
    try {
     //initialize MQ manager.
   	 queueManagerObj = new MQQueueManager(queuemanager);
    }catch (MQException e) {
       System.out.println(" java.util.mq.MQConnector MQException :: " + e.getMessage());
    }
	return queueManagerObj;
}

//Put Message to Queue
public byte[] putMessage(String message){
	MQMessage putMessage = new MQMessage();
	int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 
	MQQueueManager qManager = mqConnector.getConnection();
	try {
		 String inputQueue =  environment.getIn_queue();
		 defaultInpQueue = qManager.accessQueue(inputQueue, openOptions);
		 putMessage.format = MQC.MQFMT_STRING;
         putMessage.setVersion(MQC.MQMD_VERSION_2);
         putMessage.characterSet = 1250;
         putMessage.writeString(message);


         //putMessage.writeUTF(message);
         //specify the message options...
         MQPutMessageOptions pmo = new MQPutMessageOptions(); 
         // accept 
         // put the message on the queue
         defaultInpQueue.put(putMessage, pmo);
         System.out.println("Message placed on Input Queue : " + inputQueue );
	  } catch (Exception e) {
		  System.out.println(" putMessage :: " + e.getMessage());
	  }
	  return putMessage.messageId;
}

//get Message from queue
public String getMessage(byte[] messageId){
	 String retreivedMsg = null;
	 int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 
	 MQQueueManager qManager = mqConnector.getConnection();
	 String outqueue =  environment.getOut_queue();
	 try{
		 defaultOutQueue = qManager.accessQueue(outqueue, openOptions);
		 //get message from MQ.
         MQMessage getMessages = new MQMessage();
         getMessages.messageId = messageId;
       //get message options.
         MQGetMessageOptions gmo = new MQGetMessageOptions();
         defaultOutQueue.get(getMessages, gmo);
         //String response = getMessages.readString(getMessages.getMessageLength());
         retreivedMsg = getMessages.readString(getMessages.getMessageLength());
         System.out.println("Response:::"+ retreivedMsg); 
         //retreivedMsg = getMessages.readUTF();
	 }catch(Exception e){
		 System.out.println(" putMessage :: " + e.getMessage()); 
	 }
	 return retreivedMsg;
}
}
