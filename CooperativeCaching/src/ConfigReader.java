import java.io.File;

import edu.rit.numeric.ExponentialPrng;
import edu.rit.util.Random;

//******************************************************************************
//
// File:    ConfigReader.java
// Package: 
// Unit:    Class ConfigReader
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

public class ConfigReader {
	
	private int N_L, N_U, N_D;
	
	private static long seed;
	
	private static ExponentialPrng taskPrng;
	
	private long clientCacheSize, serverCacheSize;
	
	private static int numberOfBlocks;
	
	private static long numberOfRequests;
	
	private int blockSize;
	
	private String algorithm;
	
	private double diskAccessTime, localClientCacheAccessTime, remoteCacheAccessTime;
	
	public ConfigReader(File file) {
		
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
	public static void setSeed(long _seed) {
		seed = _seed;
	}

	/**
	 * @return the taskPrng
	 */
	public static ExponentialPrng getTaskPrng() {
		return taskPrng;
	}

	/**
	 * @param taskPrng the taskPrng to set
	 */
	public static void setTaskPrng(ExponentialPrng _taskPrng) {
		taskPrng = _taskPrng;
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
	public static void setNumberOfBlocks(int _numberOfBlocks) {
		numberOfBlocks = _numberOfBlocks;
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
	public static void setNumberOfRequests(long _numberOfRequests) {
		numberOfRequests = _numberOfRequests;
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
	
	
}
