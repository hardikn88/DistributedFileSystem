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
	
	private static int N_L, N_U, N_D;
	
	private static Random prng;
	
	private static long seed;
	
	private static ExponentialPrng taskPrng;
	
	private static long clientCacheSize, serverCacheSize;
	
	private static long numberOfBlocks;
	
	private static long numberOfRequests;
	
	private static int blockSize;
	
	private static String algorithm;
	
	private static double diskAccessTime, localClientCacheAccessTime, remoteCacheAccessTime;
	
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
	 * @return the prng
	 */
	public Random getPrng() {
		return prng;
	}

	/**
	 * @param prng the prng to set
	 */
	public void setPrng(Random prng) {
		this.prng = prng;
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
	 * @return the taskPrng
	 */
	public ExponentialPrng getTaskPrng() {
		return taskPrng;
	}

	/**
	 * @param taskPrng the taskPrng to set
	 */
	public void setTaskPrng(ExponentialPrng taskPrng) {
		this.taskPrng = taskPrng;
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
	public long getNumberOfBlocks() {
		return numberOfBlocks;
	}

	/**
	 * @param numberOfBlocks the numberOfBlocks to set
	 */
	public void setNumberOfBlocks(long numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
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
	public void setNumberOfRequests(long numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
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
