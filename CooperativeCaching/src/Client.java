//******************************************************************************
//
// File:    Client.java
// Package: 
// Unit:    Abstract Client
//
//******************************************************************************

import java.util.LinkedList;

/**
 * Class Status provides the functionality to set the status for the Resources
 * as well as to retrieve the current resource's status
 * 
 * @author Hardik Nagda
 * 
 * @version Jan 30, 2013
 * 
 */

public abstract class Client {
	
	private int clientID;
	
	public LinkedList<CacheBlockRequest> requestQueue;
	
	public Client(int clientID) {
		this.clientID = clientID;
	}
	
	public void startServing() {
		CacheBlockRequest request = requestQueue.getFirst();
		System.out.printf ("%.3f Started serving %s%n",Simulate.sim.time(), request);
		
		lookUp(request);
		requestQueue.removeFirst();
		if(requestQueue.size()>0)
			startServing();
		
	}
	
	public void lookUp(CacheBlockRequest request) {
		System.out.println("Lookup is performed for request" + request);
	}
	
	abstract public void Eviction();
	
	public String toString() {
		return "Client id: " + clientID  ;
	}
}
