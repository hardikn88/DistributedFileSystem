//******************************************************************************
//
// File:    HintBasedClient.java
// Package: 
// Unit:    Class HintBasedClient
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

public class HintBasedClient extends Client {

	public HintBasedClient(int clientID) {
		super(clientID);
	}
	
	@Override
	public void Eviction() {
		
	}

	public String toString() {
		return("Hint-Based "+ super.toString());
	}	
}
