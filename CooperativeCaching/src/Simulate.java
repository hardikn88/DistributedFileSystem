//******************************************************************************
//
// File:    Simulate.java
// Package: 
// Unit:    Class Simulate
//
//******************************************************************************

import edu.rit.numeric.ExponentialPrng;
//import edu.rit.numeric.ListXYSeries;
//import edu.rit.numeric.plot.Plot;
import edu.rit.sim.Event;
import edu.rit.sim.Simulation;
import edu.rit.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class Simulate is used to generate tasks at a random arrival rate and random[[
 * task size
 *
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

/*
 * Referred Prof. Alan Kaminsky's Book: Simulation Simplified
 */

public class Simulate {

	public static Simulation sim;
	private static Random blockPrng,clientPrng;
	private static ExponentialPrng requestPrng;
	private static long requestCount;
	//private static 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1)
			usage();
		
		String fileName = args[0];
		File file = new File(fileName);

		ConfigReader config = null;
		try {
			config = new ConfigReader(file);
		} catch (FileNotFoundException e) {
			System.err.println ("File: " + fileName +"does not exist");
			return;
		}
		
		blockPrng = Random.getInstance(config.getBlockSeed());
		clientPrng = Random.getInstance(config.getClientSeed());
		requestPrng = new ExponentialPrng(Random.getInstance(config.getRequestSeed()), config.getRequestLambda());
		
		FileSystem fs = new FileSystem(config);
		fs.SetUpServer();
		fs.SetUpManager();
		
		int N = 2;
		//for(int N = config.getN_L(); N <= config.getN_U(); N+=config.getN_D()) {
			fs.SetUpClient(N);
			fs.ClearServerCache();
			fs.ClearManagerEntries();
			
			sim = new Simulation();
			generateRequest();
			sim.run();
			
		//}
		
	}
	
    private static void usage() {
    	System.err.println ("Usage: java CooperativeCaching <filename>");
    	System.exit (1);
    }
    
    private static void generateRequest() {
    	
    	requestCount++;
    	System.out.println ("Request Count: "+ requestCount);
    	
    	Client client = forwardingClient();    	
    	CacheBlockRequest blockRequest = new CacheBlockRequest (blockPrng.nextInt (ConfigReader.getNumberOfBlocks()));
    	
    	System.out.printf ("%.3f %s request passed to client %s %n", sim.time(), blockRequest, client);
    	client.addToQueue (blockRequest);
    	
    	if(requestCount<ConfigReader.getNumberOfRequests())
    		sim.doAfter (requestPrng.next(), new Event()
    		{
    			public void perform() { generateRequest(); }
    		});
    	
    }
    
    private static Client forwardingClient() {
    	int clientID = clientPrng.nextInt(FileSystem.getNumberOfClients());
    	return FileSystem.setOfClient[clientID];
    }
    
}
