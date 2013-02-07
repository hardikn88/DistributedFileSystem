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
	
	private static int numberOfClients;
	
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
	public static int getNumberOfClients() {
		return numberOfClients;
	}
	
	/**
	 * @param numberOfClients the numberOfClients to set
	 */
	public static void setNumberOfClients(int _numberOfClients) {
		numberOfClients = _numberOfClients;
	}

	public Boolean SetUpClient(int N) {
		setNumberOfClients(N);
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
