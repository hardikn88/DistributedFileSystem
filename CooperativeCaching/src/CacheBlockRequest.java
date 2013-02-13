//******************************************************************************
//
// File:    CacheBlockRequest.java
// Package: 
// Unit:    Class CacheBlockRequest
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

public class CacheBlockRequest {
	
	private int blockID;

	/**
	 * @return the blockID
	 */
	public int getBlockID() {
		return blockID;
	}

	/**
	 * @param blockID the blockID to set
	 */
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public CacheBlockRequest (int id) {
		this.blockID = id;
	}
	
	public String toString() {
		return "Block " + blockID;
	}
}
