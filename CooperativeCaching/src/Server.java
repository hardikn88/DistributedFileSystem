import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

//******************************************************************************
//
// File:    Server.java
// Package: 
// Unit:    Class Server
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

public class Server {
	
	private long cacheSize;
	
	public Disk disk;
	
	private Map<Integer, CacheBlock> cache;
	
	public Server(long cacheSize) {
		this.cacheSize = cacheSize;
		disk = new Disk ();
		cache = Collections.synchronizedMap(new LinkedHashMap<Integer, CacheBlock>());
	}
	
	public String toString() {
		return "Server" ;
	}

	public CacheBlock performLookUpInServerCache(Client client, int requestBlockID) {
		client.blockAccessTime += ConfigReader.getClientCacheAccessTime();
		
		CacheBlock block = null;
		block = cache.get(requestBlockID);
		
		if(block!=null) 
		{
			synchronized (this) {
				block.setMasterClientHolder(client.clientID);
			}
		}
		
		return block;
	}

}
