//******************************************************************************
//
// File:    Client.java
// Package: 
// Unit:    Abstract Client
//
//******************************************************************************

import java.util.LinkedList;
import edu.rit.sim.Event;

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
		requestQueue = new LinkedList<CacheBlockRequest>();
	}

    public void addToQueue (CacheBlockRequest blockRequest) {
    	requestQueue.add (blockRequest);
    	System.out.printf ("%.3f %s received by %s%n",Simulate.sim.time(), requestQueue.toString(), this);
	    
	    if(requestQueue.size() == 1)
    		startServing();	    
    } 

	public void startServing() {
		final CacheBlockRequest request = requestQueue.getFirst();
		System.out.printf ("Client %s started serving %s at %.3f %n",this, request, Simulate.sim.time());
		lookUp(request);
		Simulate.sim.doAfter (Simulate.sim.time(), new Event()
		{
			public void perform() { 
				removeFromQueue();
			}
		});
	}
	
	private void removeFromQueue() {
		CacheBlockRequest request = requestQueue.removeFirst();
		System.out.printf ("%.3f %s removed from %s%n",
		Simulate.sim.time(), request, this);
		if(requestQueue.size()>0)
			startServing();
	}
		
	public void lookUp(CacheBlockRequest request) {
		System.out.println("Lookup is performed for request" + request);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public void Eviction();
	
	public String toString() {
		return "Client id: " + clientID  ;
	}
}
