//******************************************************************************
//
// File:    CacheBlock.java
// Package: 
// Unit:    Class CacheBlock
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

public class CacheBlock {
	
	private long blockID;
	
	/**
	 * @return the blockID
	 */
	public long getBlockID() {
		return blockID;
	}

	/**
	 * @param blockID the blockID to set
	 */
	public void setBlockID(long blockID) {
		this.blockID = blockID;
	}

	public CacheBlock (long l) {
		this.blockID = l;
	}
	
	public String toString() {
		return "Blocks " + blockID;
	}
}
