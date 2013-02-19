//******************************************************************************
//
// File:    Server.java
// Package: 
// Unit:    Class Server
//
//******************************************************************************

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	public Disk disk;
	
	private Map<Integer, CacheBlock> cache;
	
	public Server(final int cacheSize) {
		disk = new Disk ();
		cache = Collections.synchronizedMap(new LinkedHashMap<Integer, CacheBlock>(cacheSize + 1, 1.1f, true) {                                

			private static final long serialVersionUID = 1L;

			@Override
            public boolean removeEldestEntry(Map.Entry<Integer, CacheBlock> eldest)  
            {
                //when to remove the eldest entry
                return size() > cacheSize ;   //size exceeded the max allowed
            }
        });
	}
	
	public String toString() {
		return "Server" ;
	}

	public CacheBlock performLookUpInServerCache(Client client, int requestBlockID) {
		client.blockAccessTime += ConfigReader.getClientCacheAccessTime();	
		CacheBlock tempBlock = cache.get(requestBlockID);
		CacheBlock block = null;
		
		if(tempBlock!=null) 
		{
			block = new CacheBlock(tempBlock);
			synchronized (this) {
				block.setMasterClientHolder(client.clientID);
			}
		}	
		return block;
	}
}
