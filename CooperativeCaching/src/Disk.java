//******************************************************************************
//
// File:    Disk.java
// Package: 
// Unit:    Class Disk
//
//******************************************************************************

/**
 * Class Disk provides the functionality to set the status for the Resources
 * as well as to retrieve the current resource's status
 * 
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

public class Disk {
	
	public Disk() {
		
	}
	
	public String toString() {
		return "Disk" ;
	}

	public CacheBlock performLookUpInDisk(Client client, int requestBlockID) {
		
		client.blockAccessTime += ConfigReader.getDiskAccessTime();
		client.diskCacheHit++;
		
		CacheBlock block = new CacheBlock(requestBlockID);
		
		synchronized (this) {
			block.setMasterClientHolder(client.clientID);			
		}
		
		return block;
	}
}
