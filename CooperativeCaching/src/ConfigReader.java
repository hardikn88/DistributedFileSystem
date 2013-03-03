//******************************************************************************
//
// File:    ConfigReader.java
// Package: 
// Unit:    Class ConfigReader
//
//******************************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class Status provides the functionality to set the status for the Resources
 * as well as to retrieve the current resource's status
 * 
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

public class ConfigReader {
	
	private int N_L, N_U, N_D;
	
	private static long blockSeed, clientSeed, requestSeed;
	
	private double requestLambda;
	
	private long clientCacheSize, serverCacheSize, forwardingPoolSize;
	
	private static int numberOfBlocks;
	
	private static long numberOfRequests;
	
	private int epochCounter;
	
	private static int epochTimer;
	
	private int blockSize;
	
	private String algorithm;
	
	private static double diskAccessTime, clientCacheAccessTime, latencyTime; /* remoteCacheAccessTime*/;
	
	private static String conditionsforServerMemoryAlgorithm, baseCase;
	
	public ConfigReader(File file) throws FileNotFoundException {
		readFile(file);
	}

	/**
	 * @return the n_L
	 */
	public int getN_L() {
		return N_L;
	}

	/**
	 * @param n_L the n_L to set
	 */
	public void setN_L(int n_L) {
		N_L = n_L;
	}

	/**
	 * @return the n_U
	 */
	public int getN_U() {
		return N_U;
	}

	/**
	 * @param n_U the n_U to set
	 */
	public void setN_U(int n_U) {
		N_U = n_U;
	}

	/**
	 * @return the n_D
	 */
	public int getN_D() {
		return N_D;
	}

	/**
	 * @param n_D the n_D to set
	 */
	public void setN_D(int n_D) {
		N_D = n_D;
	}

	/**
	 * @return the blockSeed
	 */
	public static long getBlockSeed() {
		return blockSeed;
	}

	/**
	 * @param blockSeed the blockSeed to set
	 */
	public static void setBlockSeed(long blockSeed) {
		ConfigReader.blockSeed = blockSeed;
	}

	/**
	 * @return the clientSeed
	 */
	public static long getClientSeed() {
		return clientSeed;
	}

	/**
	 * @param clientSeed the clientSeed to set
	 */
	public static void setClientSeed(long clientSeed) {
		ConfigReader.clientSeed = clientSeed;
	}

	/**
	 * @return the requestSeed
	 */
	public static long getRequestSeed() {
		return requestSeed;
	}

	/**
	 * @param requestSeed the requestSeed to set
	 */
	public static void setRequestSeed(long requestSeed) {
		ConfigReader.requestSeed = requestSeed;
	}

	/**
	 * @return the requestLambda
	 */
	public double getRequestLambda() {
		return requestLambda;
	}

	/**
	 * @param requestLambda the requestLambda to set
	 */
	public void setRequestLambda(double requestLambda) {
		this.requestLambda = requestLambda;
	}

	/**
	 * @return the clientCacheSize
	 */
	public long getClientCacheSize() {
		return clientCacheSize;
	}

	/**
	 * @param clientCacheSize the clientCacheSize to set
	 */
	public void setClientCacheSize(long clientCacheSize) {
		this.clientCacheSize = clientCacheSize;
	}

	/**
	 * @return the serverCacheSize
	 */
	public long getServerCacheSize() {
		return serverCacheSize;
	}

	/**
	 * @param serverCacheSize the serverCacheSize to set
	 */
	public void setServerCacheSize(long serverCacheSize) {
		this.serverCacheSize = serverCacheSize;
	}

	/**
	 * @return the forwardingPoolSize
	 */
	public long getForwardingPoolSize() {
		return forwardingPoolSize;
	}

	/**
	 * @param forwardingPoolSize the forwardingPoolSize to set
	 */
	public void setForwardingPoolSize(long forwardingPoolSize) {
		this.forwardingPoolSize = forwardingPoolSize;
	}

	/**
	 * @return the numberOfBlocks
	 */
	public static int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	/**
	 * @param numberOfBlocks the numberOfBlocks to set
	 */
	public static void setNumberOfBlocks(int numberOfBlocks) {
		ConfigReader.numberOfBlocks = numberOfBlocks;
	}

	/**
	 * @return the numberOfRequests
	 */
	public static long getNumberOfRequests() {
		return numberOfRequests;
	}

	/**
	 * @param numberOfRequests the numberOfRequests to set
	 */
	public static void setNumberOfRequests(long numberOfRequests) {
		ConfigReader.numberOfRequests = numberOfRequests;
	}

	/**
	 * @return the blockSize
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * @param blockSize the blockSize to set
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @return the diskAccessTime
	 */
	public static double getDiskAccessTime() {
		return diskAccessTime;
	}

	/**
	 * @param diskAccessTime the diskAccessTime to set
	 */
	public static void setDiskAccessTime(double diskAccessTime) {
		ConfigReader.diskAccessTime = diskAccessTime;
	}

	/**
	 * @return the localClientCacheAccessTime
	 */
	public static double getClientCacheAccessTime() {
		return clientCacheAccessTime;
	}

	/**
	 * @param localClientCacheAccessTime the localClientCacheAccessTime to set
	 */
	public static void setClientCacheAccessTime(double clientCacheAccessTime) {
		ConfigReader.clientCacheAccessTime = clientCacheAccessTime;
	}

	/**
	 * @return the remoteCacheAccessTime
	 */
/*	public double getRemoteCacheAccessTime() {
		return remoteCacheAccessTime;
	}*/

	/**
	 * @param remoteCacheAccessTime the remoteCacheAccessTime to set
	 */
/*	public void setRemoteCacheAccessTime(double remoteCacheAccessTime) {
		this.remoteCacheAccessTime = remoteCacheAccessTime;
	}*/
	
	public static double getLatencyTime() {
		return latencyTime;
	}

	public static void setLatencyTime(double latencyTime) {
		ConfigReader.latencyTime = latencyTime;
	}

	/**
	 * @return the epochCounter
	 */
	public int getEpochCounter() {
		return epochCounter;
	}

	/**
	 * @param epochCounter the epochCounter to set
	 */
	public void setEpochCounter(int epochCounter) {
		this.epochCounter = epochCounter;
	}

	/**
	 * @return the epochTimer
	 */
	public static int getEpochTimer() {
		return epochTimer;
	}

	/**
	 * @param epochTimer the epochTimer to set
	 */
	public static void setEpochTimer(int epochTimer) {
		ConfigReader.epochTimer = epochTimer;
	}

	/**
	 * @return the conditionsforServerMemoryAlgorithm
	 */
	public static String getConditionsforServerMemoryAlgorithm() {
		return conditionsforServerMemoryAlgorithm;
	}

	/**
	 * @param conditionsforServerMemoryAlgorithm the conditionsforServerMemoryAlgorithm to set
	 */
	public static void setConditionsforServerMemoryAlgorithm(
			String conditionsforServerMemoryAlgorithm) {
		ConfigReader.conditionsforServerMemoryAlgorithm = conditionsforServerMemoryAlgorithm;
	}

	/**
	 * @return the baseCase
	 */
	public static String getBaseCase() {
		return baseCase;
	}

	/**
	 * @param baseCase the baseCase to set
	 */
	public static void setBaseCase(String baseCase) {
		ConfigReader.baseCase = baseCase;
	}

	public void readFile(File file) throws FileNotFoundException {
		
		System.out.println("Configuration");
		Scanner scanner = new Scanner(file);
		if (scanner.hasNextLine()) {
			setN_L(Integer.parseInt(scanner.nextLine()));
			System.out.println("N_L:" + getN_L());
		}
		else {
				System.err.println("File is empty");
				System.exit (1);
		}
		
		if (scanner.hasNextLine()) {
			setN_U(Integer.parseInt(scanner.nextLine()));
			System.out.println("N_U:" + getN_U());
		}
		
		if (scanner.hasNextLine()) {
			setN_D(Integer.parseInt(scanner.nextLine()));
			System.out.println("N_D:" + getN_D());
		}
		
		if (scanner.hasNextLine()) {
			setClientCacheSize(Long.parseLong(scanner.nextLine()));
			System.out.println("Client Cache Size:" + getClientCacheSize());
		}
		
		if (scanner.hasNextLine()) {
			setServerCacheSize(Long.parseLong(scanner.nextLine()));
			System.out.println("Server Cache Size:" + getServerCacheSize());
		}
		
		if (scanner.hasNextLine()) {
			setForwardingPoolSize(Long.parseLong(scanner.nextLine()));
			System.out.println("Forwarding Pool:" + getForwardingPoolSize());
		}
		
		if (scanner.hasNextLine()) {
			setBlockSize(Integer.parseInt(scanner.nextLine()));
			System.out.println("Block Size:" + getBlockSize());
		}
		
		if (scanner.hasNextLine()) {
			setNumberOfBlocks(Integer.parseInt(scanner.nextLine()));
			System.out.println("Number of Blocks:" + getNumberOfBlocks());
		}
		
		if (scanner.hasNextLine()) {
			setNumberOfRequests(Long.parseLong(scanner.nextLine()));
			System.out.println("Number of Requests" + getNumberOfRequests());
		}
		
		if (scanner.hasNextLine()) {
			setDiskAccessTime(Double.parseDouble(scanner.nextLine()));
			System.out.println("Disk Access Time:" + getDiskAccessTime());
		}
		
		if (scanner.hasNextLine()) {
			setClientCacheAccessTime(Double.parseDouble(scanner.nextLine()));
			System.out.println("Client Cache Access Time:" + getClientCacheAccessTime());
		}
		
		if (scanner.hasNextLine()) {
			setLatencyTime(Double.parseDouble(scanner.nextLine()));
			System.out.println("Latency Time" + getLatencyTime());
		}
		
		if (scanner.hasNextLine()) {
			setAlgorithm((scanner.nextLine()));
			System.out.println("Algorithm:" + getAlgorithm());
		}
		
		if (scanner.hasNextLine()) {
			setBlockSeed(Long.parseLong(scanner.nextLine()));
			System.out.println("Block Seed:" + getBlockSeed());
		}
		
		if (scanner.hasNextLine()) {
			setClientSeed(Long.parseLong(scanner.nextLine()));
			System.out.println("Client Seed:" + getClientSeed());
		}
		
		if (scanner.hasNextLine()) {
			setRequestSeed(Long.parseLong(scanner.nextLine()));
			System.out.println("Request Seed:" + getRequestSeed());
		}
		
		if (scanner.hasNextLine()) {
			setRequestLambda(Double.parseDouble(scanner.nextLine()));
			System.out.println("Request Lambda:" + getRequestLambda());
		}
		
		if (scanner.hasNextLine()) {
			setEpochCounter(Integer.parseInt(scanner.nextLine()));
			System.out.println("Epoch Counter:" + getEpochCounter());
		}
		
		if (scanner.hasNextLine()) {
			setEpochTimer(Integer.parseInt(scanner.nextLine()));
			System.out.println("Epoch Timer:" + getEpochTimer());
		}
		
		if (scanner.hasNextLine()) {
			setConditionsforServerMemoryAlgorithm(scanner.nextLine());
			System.out.println("Condition for server memory algorithm:" + getConditionsforServerMemoryAlgorithm());
		}
		
		if (scanner.hasNextLine()) {
			setBaseCase(scanner.nextLine());
			System.out.println("Base Case:" + getBaseCase());
		}
	}
}
