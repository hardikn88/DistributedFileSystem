//******************************************************************************
//
// File:    Client.java
// Package: 
// Unit:    Abstract Client
//
//******************************************************************************


import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
	
	public int clientID;
	
	public double blockAccessTime;
	
	public long localCacheHit, remoteCacheHit, diskCacheHit;
	
	public LinkedList<CacheBlockRequest> requestQueue;
	
	private Map<Integer, CacheBlock> cache;
	
	private Hashtable<Integer, Integer> hints;
	
	public Client(int clientID) {
		
		this.clientID = clientID;
		
		this.localCacheHit = 0;
		
		this.remoteCacheHit = 0;
		
		this.diskCacheHit = 0;
		
		this.blockAccessTime = 0.0;
		
		requestQueue = new LinkedList<CacheBlockRequest>();
		
		hints = new Hashtable<Integer, Integer>();
		
		cache = Collections.synchronizedMap(new LinkedHashMap<Integer, CacheBlock>());
		
	}

    public void addToQueue (CacheBlockRequest blockRequest) {
    	requestQueue.add (blockRequest);
    	System.out.printf ("%.3f %s received by %s%n",Simulate.sim.time(), requestQueue.toString(), this);
	    
	    if(requestQueue.size() == 1)
    		startServing();	    
    } 

	public void startServing() {
		final CacheBlockRequest request = requestQueue.getFirst();
		CacheBlock block = lookUp(request);
		
		if(block.IsMasterBlock())
		{	
			FileSystem.manager.updateHintsOfManager(block.getBlockID(), this.clientID);
			hints.put(block.getBlockID(), this.clientID);//redundant
		}	
		
		this.cache.put(block.getBlockID(), block);
		System.out.printf ("Client %s contains hint %s%n",this, hints.toString());
		System.out.printf ("Client %s contains cache %s%n",this, cache.toString());
		
		Simulate.sim.doAfter (Simulate.sim.time(), new Event()
		{
			public void perform() { 
				removeFromQueue();
			}
		});
	}
	
	private void removeFromQueue() {
		requestQueue.removeFirst();
		//System.out.printf ("%.3f %s removed from %s%n",
		//Simulate.sim.time(), request, this);
		if(requestQueue.size()>0)
			startServing();
	}
		
	public CacheBlock lookUp(CacheBlockRequest request) {
		int requestBlockID = request.getBlockID();
		System.out.println("Lookup is performed for request " + requestBlockID + " by Client " + this);
		CacheBlock block = null;
		block = performLocalLookup(this, requestBlockID);
		
		if(block != null)
		{
			this.localCacheHit++;
			return block;
		}
		
		int clientid = -1;
		
		int requestCount = 0;
		
		if(hints.containsKey(requestBlockID))
			clientid = hints.get(requestBlockID);
		else if (FileSystem.manager.containsHint(requestBlockID))
			clientid = FileSystem.manager.getHint(requestBlockID);
		
		if(clientid != -1)
			block = FileSystem.setOfClient[clientid].remoteLookUp(this, requestBlockID, requestCount+1);
		
		if(block != null)
		{
			hints.put(block.getBlockID(), block.getMasterClientHolder());
			return block;
		}
		
		block = fetchFromServerCache(this, requestBlockID);
		
		if(block != null)
			return block;
			
		return fetchFromDisk(this, requestBlockID);		
	}
	
	public CacheBlock remoteLookUp(Client client, int requestBlockID, int requestCount) {
		
		System.out.println("Remote Lookup is performed for request " + requestBlockID + " by Client " + client);
		
		client.blockAccessTime += ConfigReader.getLatencyTime();
		
		CacheBlock block = null;
		block = performLocalLookup(client, requestBlockID);
		
		if(block != null)
		{
			block.setMasterBlock(false);
			block.setMasterClientHolder(this.clientID);
			client.remoteCacheHit++;
			return block;
		}
		
		int clientid = -1;
		
		if(hints.containsKey(requestBlockID))
			clientid = hints.get(requestBlockID);
		else if (FileSystem.manager.containsHint(requestBlockID))
				clientid = FileSystem.manager.getHint(requestBlockID);
		
		if(clientid != -1)
			block = FileSystem.setOfClient[clientid].remoteLookUp(client, requestBlockID, requestCount+1);
		
		if(block != null)
		{
			hints.put(block.getBlockID(), block.getMasterClientHolder());
			return block;
		}
		
		block = fetchFromServerCache(client, requestBlockID);
		
		if(block != null)
			return block;
			
		return fetchFromDisk(client, requestBlockID);
	}

	private CacheBlock fetchFromServerCache(Client client, int requestBlockID) {
		CacheBlock block = null; 
		block =	FileSystem.server.performLookUpInServerCache(client, requestBlockID);
		
		if(block != null)
		{
			client.remoteCacheHit++;
			hints.put(block.getBlockID(), block.getMasterClientHolder());
		}
		
		return block;
	}
	
	private CacheBlock fetchFromDisk(Client client, int requestBlockID) {
		CacheBlock block = FileSystem.server.disk.performLookUpInDisk(client, requestBlockID);
		hints.put(block.getBlockID(), block.getMasterClientHolder());	
		return block;
	}
	
	public CacheBlock performLocalLookup(Client client, int requestBlockID) {
		client.blockAccessTime += ConfigReader.getClientCacheAccessTime();
		return cache.get(requestBlockID);
	}
	
	abstract public void Eviction();
	
	public String toString() {
		return "Client id: " + clientID  ;
	}
}
