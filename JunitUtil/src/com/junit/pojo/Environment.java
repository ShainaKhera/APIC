/**
 * 
 */
package com.junit.pojo;

/**
 * @author jaya.c.atmakuri
 *
 */
public class Environment {
private String hostName;
private String queuemanager;
private String channel;
private String port;
private String user;
private String password;
private String in_queue;
private String out_queue;
private String message;
private String url;
public String protocol;
/**
 * @param hostName the hostName to set
 */
public void setHostName(String hostName) {
	this.hostName = hostName;
}
/**
 * @param queuemanager the queuemanager to set
 */
public void setQueuemanager(String queuemanager) {
	this.queuemanager = queuemanager;
}
/**
 * @param channel the channel to set
 */
public void setChannel(String channel) {
	this.channel = channel;
}
/**
 * @param port the port to set
 */
public void setPort(String port) {
	this.port = port;
}
/**
 * @param user the user to set
 */
public void setUser(String user) {
	this.user = user;
}
/**
 * @param password the password to set
 */
/**
 * @param protocol to set
 */
public void setProtocol(String protocol) {
	this.protocol = protocol;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @param in_queue the in_queue to set
 */
public void setIn_queue(String in_queue) {
	this.in_queue = in_queue;
}
/**
 * @param out_queue the out_queue to set
 */
public void setOut_queue(String out_queue) {
	this.out_queue = out_queue;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}
/**
 * @param url the url to set
 */
public void setUrl(String url) {
	this.url = url;
}
/**
 * @return the hostName
 */
public String getHostName() {
	return hostName;
}
/**
 * @return the queuemanager
 */
public String getQueuemanager() {
	return queuemanager;
}
/**
 * @return the channel
 */
public String getChannel() {
	return channel;
}
/**
 * @return the port
 */
public String getPort() {
	return port;
}
/**
 * @return the user
 */
public String getUser() {
	return user;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @return the in_queue
 */
public String getIn_queue() {
	return in_queue;
}
/**
 * @return the out_queue
 */
public String getOut_queue() {
	return out_queue;
}
/**
 * @return the message
 */
public String getMessage() {
	return message;
}
/**
 * @return the url
 */
public String getUrl() {
	return url;
}
public String getProtocol() {
	return protocol;
}
}
