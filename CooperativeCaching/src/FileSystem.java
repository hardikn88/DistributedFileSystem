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
	
	public static Client[] setOfClient;
	
	public static Server server;
	
	public static Manager manager;
	
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
	public static void setNumberOfClients(int numberOfClients) {
		FileSystem.numberOfClients = numberOfClients;
	}

	public void SetUpClient(int N) {
		setNumberOfClients(N);
		System.out.println("Number of Clients: " + N);
		
		setOfClient = new Client[N];
		
		for(int i = 0 ; i < N ; i++)
			if(config.getAlgorithm().equals("hint-based"))
				setOfClient[i] = new HintBasedClient(i);
			else if(config.getAlgorithm().equals("locality-based"))
					setOfClient[i] = new LACClient(i);
			else if(config.getAlgorithm().equals("servermemory-based"))
					setOfClient[i] = new UsingServerMemoryClient(i);
	}
	
	public void SetUpManager() {
		manager = new Manager();
	}
	
	public void SetUpServer() {
		server = new Server(config.getServerCacheSize());
	}
	
	public void ClearServerCache() {
		
	}
	
	public void ClearManagerEntries() {
		
	}
	
}
