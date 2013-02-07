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
	
	private long blockID;

	public CacheBlockRequest (long l) {
		this.blockID = l;
	}
	
	public String toString() {
		return "Block " + blockID;
	}
}
