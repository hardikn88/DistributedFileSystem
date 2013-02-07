//******************************************************************************
//
// File:    Simulate.java
// Package: 
// Unit:    Class Simulate
//
//******************************************************************************

import java.io.File;
import java.util.LinkedList;

import edu.rit.numeric.ExponentialPrng;
import edu.rit.numeric.ListXYSeries;
import edu.rit.numeric.UniformPrng;
import edu.rit.numeric.plot.Plot;
import edu.rit.sim.Event;
import edu.rit.sim.Simulation;
import edu.rit.util.Random;

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
	private static LinkedList<CacheBlockRequest> requestQueue;
	public static Random prng;
	//private static 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1)
			usage();
		
		String fileName = args[0];
		File file = new File(fileName);
		
		if(!file.exists()) {
			System.err.println ("File: " + fileName +"does not exist");
			return;
		}
		
		ConfigReader config = new ConfigReader(file);
		
		FileSystem fs = new FileSystem(config);
		fs.SetUpServer();
		fs.SetUpManager();
		
		long numberOfClients = config.getN_L();
		prng = Random.getInstance(config.getSeed());
		
		for(long N = numberOfClients; N <= config.getN_U(); N+=config.getN_D()) {
			fs.SetUpClient();
			fs.ClearServerCache();
			fs.ClearManagerEntries();
						
			sim = new Simulation();
			generateRequest();
			
		}
		
	}
	
    private static void usage() {
    	System.err.println ("Usage: java CooperativeCaching <filename>");
    	System.exit (1);
    }
    
    private static void generateRequest() {
    	addToQueue (new CacheBlockRequest (prng.nextLong(ConfigReader.getNumberOfRequests())));
    	
    	
    }
    
    private static void addToQueue (CacheBlockRequest blockRequest) {
	    System.out.printf ("%.3f %s added to queue%n", sim.time(), blockRequest);
	    requestQueue.add (blockRequest);
	    //if (requestQueue.size() == 1) 
	    	//startServing();
    } 
    
    
}
