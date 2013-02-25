//******************************************************************************
//
// File:    MetaDataofBlock.java
// Package: 
// Unit:    Class MetaDataofBlock
//
//******************************************************************************

/**
 * Class MetaDataofBlock provides the functionality to set the status for the Resources
 * as well as to retrieve the current resource's status
 * 
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

public class MetaDataofBlock {
	
	private int blockID;
	private double recency;
	private BlockType blockType;
	
	public MetaDataofBlock(MetaDataofBlock block) {
		this.blockID = block.blockID;
		this.blockType = block.blockType;
		this.recency = block.recency;
	}
	
	public MetaDataofBlock(int blockID) {
		this.blockID = blockID;
		this.recency = -1.0;
		this.blockType = BlockType.NOTPRESENT;
	}

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
	 * @return the recency
	 */
	public double getRecency() {
		return recency;
	}

	/**
	 * @param recency the recency to set
	 */
	public void setRecency(double recency) {
		this.recency = recency;
	}

	/**
	 * @return the blocktype
	 */
	public BlockType getBlockType() {
		return blockType;
	}

	/**
	 * @param blocktype the blocktype to set
	 */
	public void setBlockType(BlockType blockType) {
		this.blockType = blockType;
	}

	public String toString() {
		return(blockType + " Block " + blockID + " : "+ recency);
	}
}