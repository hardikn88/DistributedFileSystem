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

	public LACClient(int clientID) {
		super(clientID);
	}

	@Override
	public void Eviction() {
		
	}

	public String toString() {
		return("LAC-Based "+ super.toString());
	}
	
}