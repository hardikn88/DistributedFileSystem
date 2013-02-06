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
	
	public CacheBlock (int blockID) {
		this.blockID = blockID;
	}
	
	public String toString() {
		return "Blocks " + blockID;
	}
}
