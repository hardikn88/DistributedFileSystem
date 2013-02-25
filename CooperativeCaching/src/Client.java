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
	
	protected Map<Integer, CacheBlock> cache;
	
	private Hashtable<Integer, Integer> hints;
	
	private int clientCapacity;
	
	public Client(int clientID, final int cacheSize, final int clientCapacity) {
		
		this.clientID = clientID;
		
		this.localCacheHit = 0;
		
		this.remoteCacheHit = 0;
		
		this.diskCacheHit = 0;
		
		this.blockAccessTime = 0.0;
		
		this.requestQueue = new LinkedList<CacheBlockRequest>();
		
		this.hints = new Hashtable<Integer, Integer>();
		
		this.clientCapacity = clientCapacity;
		
		this.cache = Collections.synchronizedMap(new LinkedHashMap<Integer, CacheBlock>(cacheSize, 1.1f, true));	
	}

    public void addToQueue (CacheBlockRequest blockRequest) {
    	requestQueue.add (blockRequest);
    	//System.out.printf ("%.3f %s received by %s%n",Simulate.sim.time(), requestQueue.toString(), this);
	    
	    if(requestQueue.size() == 1)
    		startServing();	    
    } 

	public void startServing() {
		final CacheBlockRequest request = requestQueue.getFirst();
		CacheBlock block = lookUp(request);
		
		//System.out.println("Block "+block.getBlockID()+" obtained is "+block.IsMasterBlock());
		
		if(block.IsMasterBlock())
			FileSystem.manager.updateHintsOfManager(block.getBlockID(), this.clientID);
				
		this.Eviction(block);
		
		System.out.printf ("Client %s contains hint %s%n",this, hints.toString());
		System.out.printf ("Client %s contains cache %s%n",this, cache.toString());
		
		Simulate.sim.doAfter (Simulate.sim.time(), new Event() {
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
		
		CacheBlock block = performLocalLookup(this, requestBlockID);
		
		if(block != null) {
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
		
		if(block != null) {
			updateHints(block.getBlockID(), block.getMasterClientHolder());
			return block;
		}
		
		block = fetchFromServerCache(this, requestBlockID);
		
		if(block != null)
			return block;
			
		return fetchFromDisk(this, requestBlockID);		
	}
	
	public CacheBlock remoteLookUp(Client client, int requestBlockID, int requestCount) {
		
		//System.out.println("Remote Lookup is performed for request " + requestBlockID + " by Client " + client);
		
		client.blockAccessTime += ConfigReader.getLatencyTime();
		
		CacheBlock block = null;

		if(requestCount < 10) {		
			block = performLocalLookup(client, requestBlockID);
			
			if(block != null) {
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
			
			if(block != null) {
				updateHints(block.getBlockID(), block.getMasterClientHolder());
				return block;
			}			
		}
				
		block = fetchFromServerCache(client, requestBlockID);
		
		if(block != null)
			return block;
			
		return fetchFromDisk(client, requestBlockID);
	}

	private CacheBlock fetchFromServerCache(Client client, int requestBlockID) {
		CacheBlock block = FileSystem.server.performLookUpInServerCache(client, requestBlockID);
		
		if(block != null) {
			client.remoteCacheHit++;
			updateHints(block.getBlockID(), block.getMasterClientHolder());
		}
		return block;
	}
	
	private CacheBlock fetchFromDisk(Client client, int requestBlockID) {
		CacheBlock block = FileSystem.server.disk.performLookUpInDisk(client, requestBlockID);
		updateHints(block.getBlockID(), block.getMasterClientHolder());	
		return block;
	}
	
	public CacheBlock performLocalLookup(Client client, int requestBlockID) {
		client.blockAccessTime += ConfigReader.getClientCacheAccessTime();
		CacheBlock tempBlock = cache.get(requestBlockID);
		CacheBlock block = null;
		
		if(tempBlock!= null)
		{
			tempBlock.setAccessTime(Simulate.sim.time());
			//System.out.println("block "+ requestBlockID + " access Time is " + cache.get(requestBlockID).getAccessTime());
			block = new CacheBlock(tempBlock);
		}
		return block;
	}
	
	public Boolean cacheContainsBlock(CacheBlock block) {
		return cache.containsKey(block.getBlockID());
	}
	
	public Boolean cacheFull() {
		//System.out.println("cache size " + cache.size());
		return this.cache.size() >= this.clientCapacity; 
	}
	
	protected void updateHints(int masterBlockID, int masterBlockHoldingClient) {
		this.hints.put(masterBlockID, masterBlockHoldingClient);
	}
	
	protected void removeHints(int masterBlockID) {
		this.hints.remove(masterBlockID);
		FileSystem.manager.removeHintsOfManager(masterBlockID);
	}
	
	abstract public void Eviction(CacheBlock block);
	
	abstract public CacheBlock getForwardingBlock();
	
	abstract public CacheBlock removeForwardingBlock();
	
	abstract public void forwardBlock(CacheBlock forwardedBlock);
	
	public String toString() {
		return "Client id: " + clientID  ;
	}
}
