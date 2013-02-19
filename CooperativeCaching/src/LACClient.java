//******************************************************************************
//
// File:    LACClient.java
// Package: 
// Unit:    Class LACClient
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

public class LACClient extends Client  {

	public LACClient(int clientID, final int cacheSize) {
		super(clientID, cacheSize);
	}

	@Override
	public void Eviction(CacheBlock block) {
		
	}

	@Override
	public CacheBlock getForwardingBlock() {
		return null;
	}

	@Override
	public CacheBlock removeForwardingBlock() {
		return null;
	}

	
	@Override
	public void forwardBlock(CacheBlock forwardedBlock) {
		
	}
	
	public String toString() {
		return("LAC-Based "+ super.toString());
	}
	
}
