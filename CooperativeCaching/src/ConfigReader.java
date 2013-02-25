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
	
	private long blockSeed, clientSeed, requestSeed;
	
	private double requestLambda;
	
	private long clientCacheSize, serverCacheSize, forwardingPoolSize;
	
	private static int numberOfBlocks;
	
	private static long numberOfRequests;
	
	private int blockSize;
	
	private String algorithm;
	
	private static double diskAccessTime, clientCacheAccessTime, latencyTime; /* remoteCacheAccessTime*/;
	
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
	public long getBlockSeed() {
		return blockSeed;
	}

	/**
	 * @param blockSeed the blockSeed to set
	 */
	public void setBlockSeed(long blockSeed) {
		this.blockSeed = blockSeed;
	}

	/**
	 * @return the clientSeed
	 */
	public long getClientSeed() {
		return clientSeed;
	}

	/**
	 * @param clientSeed the clientSeed to set
	 */
	public void setClientSeed(long clientSeed) {
		this.clientSeed = clientSeed;
	}

	/**
	 * @return the requestSeed
	 */
	public long getRequestSeed() {
		return requestSeed;
	}

	/**
	 * @param requestSeed the requestSeed to set
	 */
	public void setRequestSeed(long requestSeed) {
		this.requestSeed = requestSeed;
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

	public void readFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		if (scanner.hasNextLine())
			setN_L(Integer.parseInt(scanner.nextLine()));
		else {
				System.err.println("File is empty");
				System.exit (1);
		}
		
		if (scanner.hasNextLine())
			setN_U(Integer.parseInt(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setN_D(Integer.parseInt(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setClientCacheSize(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setServerCacheSize(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setForwardingPoolSize(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setBlockSize(Integer.parseInt(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setNumberOfBlocks(Integer.parseInt(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setNumberOfRequests(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setDiskAccessTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setClientCacheAccessTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setLatencyTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setAlgorithm((scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setBlockSeed(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setClientSeed(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setRequestSeed(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setRequestLambda(Double.parseDouble(scanner.nextLine()));		
	}
}
