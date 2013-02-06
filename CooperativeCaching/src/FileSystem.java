//******************************************************************************
//
// File:    FileSystem.java
// Package: 
// Unit:    Class FileSystem
//
//******************************************************************************

/**
 * Class Status provides the functionality to set the status for the Resources
 * as well as to retrieve the current resource's status
 * 
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

public class FileSystem {
	
	private int numberOfClients;
	
	private Client[] SetofClient;
	
	private Server[] SetofServer;
	
	private Manager[] SetofManager;
	
	private ConfigReader config;
		
	public FileSystem(ConfigReader config) {
		this.config = config;
	}
	
	/**
	 * @return the numberOfClients
	 */
	public int getNumberOfClients() {
		return numberOfClients;
	}
	
	/**
	 * @param numberOfClients the numberOfClients to set
	 */
	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public Boolean SetUpClient() {
		return null;
	}
	
	public Boolean SetUpManager() {
		return null;
	}
	
	public Boolean SetUpServer() {
		return null;
	}
	
	public Boolean ClearServerCache() {
		return null;
	}
	
	public Boolean ClearManagerEntries() {
		return null;
	}
	
}
