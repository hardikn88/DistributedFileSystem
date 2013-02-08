//******************************************************************************
//
// File:    UsingServerMemoryClient.java
// Package: 
// Unit:    Class UsingServerMemoryClient
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

public class UsingServerMemoryClient extends Client {

	public UsingServerMemoryClient(int clientID) {
		super(clientID);
	}

	@Override
	public void Eviction() {
		
	}

	public String toString() {
		return("ServerMemory-Based "+ super.toString());
	}

}
