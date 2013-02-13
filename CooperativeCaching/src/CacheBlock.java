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
	
	private int blockID;
	
	private int masterClientHolder;
	
	private Boolean masterBlock;
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

	/**
	 * @return the masterClientHolder
	 */
	public int getMasterClientHolder() {
		return masterClientHolder;
	}

	/**
	 * @param masterClientHolder the masterClientHolder to set
	 */
	public void setMasterClientHolder(int masterClientHolder) {
		this.masterClientHolder = masterClientHolder;
	}

	/**
	 * @param masterBlock the masterBlock to set
	 */
	public void setMasterBlock(Boolean value) {
		this.masterBlock = value;
	}

	public CacheBlock (int blockID) {
		this.blockID = blockID;
		setMasterClientHolder(-1);
		setMasterBlock(true);
	}
	
	public Boolean IsMasterBlock() {
		return this.masterBlock;
	}
	
	public String toString() {
		return "Blocks " + blockID;
	}
}
