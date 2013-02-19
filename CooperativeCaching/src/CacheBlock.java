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
	
	private double accessTime;
	
	private int holdingClient;
	
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

	/**
	 * @return the accessTime
	 */
	public double getAccessTime() {
		return accessTime;
	}

	/**
	 * @param accessTime the accessTime to set
	 */
	public void setAccessTime(double accessTime) {
		this.accessTime = accessTime;
	}

	/**
	 * @return the holdingClient
	 */
	public int getHoldingClient() {
		return holdingClient;
	}

	/**
	 * @param holdingClient the holdingClient to set
	 */
	public void setHoldingClient(int holdingClient) {
		this.holdingClient = holdingClient;
	}

	public CacheBlock(CacheBlock block) {
		if(block != null)
		{
			this.blockID = block.blockID;
			this.masterClientHolder = block.masterClientHolder;
			this.holdingClient = block.holdingClient;
			this.masterBlock = block.masterBlock;
			this.accessTime= block.accessTime;
		}
	}
	
	public CacheBlock (int blockID) {
		setBlockID(blockID);
		setMasterClientHolder(-1);
		setHoldingClient(-1);
		setMasterBlock(true);
		setAccessTime(0.0);
	}
	
	public Boolean IsMasterBlock() {
		return this.masterBlock;
	}
	
	public String toString() {
		return "Blocks " + blockID;
	}
}
