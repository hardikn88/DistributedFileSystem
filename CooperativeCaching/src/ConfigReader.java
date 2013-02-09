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
	
	private long seed;
	
	private double requestLambda;
	
	private long clientCacheSize, serverCacheSize;
	
	private static int numberOfBlocks;
	
	private static long numberOfRequests;
	
	private int blockSize;
	
	private String algorithm;
	
	private double diskAccessTime, localClientCacheAccessTime, remoteCacheAccessTime;
	
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
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(long seed) {
		this.seed = seed;
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
	public double getDiskAccessTime() {
		return diskAccessTime;
	}

	/**
	 * @param diskAccessTime the diskAccessTime to set
	 */
	public void setDiskAccessTime(double diskAccessTime) {
		this.diskAccessTime = diskAccessTime;
	}

	/**
	 * @return the localClientCacheAccessTime
	 */
	public double getLocalClientCacheAccessTime() {
		return localClientCacheAccessTime;
	}

	/**
	 * @param localClientCacheAccessTime the localClientCacheAccessTime to set
	 */
	public void setLocalClientCacheAccessTime(double localClientCacheAccessTime) {
		this.localClientCacheAccessTime = localClientCacheAccessTime;
	}

	/**
	 * @return the remoteCacheAccessTime
	 */
	public double getRemoteCacheAccessTime() {
		return remoteCacheAccessTime;
	}

	/**
	 * @param remoteCacheAccessTime the remoteCacheAccessTime to set
	 */
	public void setRemoteCacheAccessTime(double remoteCacheAccessTime) {
		this.remoteCacheAccessTime = remoteCacheAccessTime;
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
			setNumberOfBlocks(Integer.parseInt(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setNumberOfRequests(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setDiskAccessTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setLocalClientCacheAccessTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setRemoteCacheAccessTime(Double.parseDouble(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setAlgorithm((scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setSeed(Long.parseLong(scanner.nextLine()));
		
		if (scanner.hasNextLine())
			setRequestLambda(Double.parseDouble(scanner.nextLine()));		
	}
}
