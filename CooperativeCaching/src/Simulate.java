//******************************************************************************
//
// File:    Simulate.java
// Package: 
// Unit:    Class Simulate
//
//******************************************************************************

import java.io.File;
import java.io.FileNotFoundException;
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
	public static Random blockPrng,clientPrng;
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
		
		blockPrng = Random.getInstance(config.getSeed());
		clientPrng = Random.getInstance(config.getSeed());
		requestPrng = new ExponentialPrng(Random.getInstance(config.getSeed()), config.getRequestLambda());
		
		FileSystem fs = new FileSystem(config);
		fs.SetUpServer();
		fs.SetUpManager();
		
		for(int N = config.getN_L(); N <= config.getN_U(); N+=config.getN_D()) {
			fs.SetUpClient(N);
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
    	requestCount++;
    	System.out.println("Count of Request "+ requestCount);
    	addToQueue (new CacheBlockRequest (blockPrng.nextInt(ConfigReader.getNumberOfBlocks())));
    	if(requestCount<ConfigReader.getNumberOfRequests());
    		
    	
    }
    
    private static void addToQueue (CacheBlockRequest blockRequest) {
	    System.out.printf ("%.3f %s added to queue%n", sim.time(), blockRequest);
	    requestQueue.add (blockRequest);
	    //if (requestQueue.size() == 1) 
	    	//startServing();
    } 
    
    
}
