import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
	
	public HintBasedClient(int clientID, final int cacheSize, Boolean fillCache) {
		super(clientID, cacheSize, cacheSize, fillCache);	
	}
	
	@Override
	public void Eviction(CacheBlock block) {
		CacheBlock forwardingBlock= null;
		int victimClient = -1;
		if(!cacheContainsBlock(block)) 
		{	
			if(cacheFull()) 
			{
				forwardingBlock = this.removeForwardingBlock();
				//System.out.println("Forwarding Block is : "+ forwardingBlock);
				if(forwardingBlock.IsMasterBlock())
				{
					victimClient = findClientWithOldestBlock(forwardingBlock);
					//System.out.println("Victim Client is "+ FileSystem.setOfClient[victimClient]);
					if(victimClient != this.clientID) 
					{
						FileSystem.setOfClient[victimClient].forwardBlock(forwardingBlock);
						super.updateHints(forwardingBlock.getBlockID(), victimClient);
						FileSystem.manager.updateHintsOfManager(forwardingBlock.getBlockID(), victimClient);
					}
					else
						super.removeHints(forwardingBlock.getBlockID());
				}
			}			
			block.setAccessTime(Simulate.sim.time());
			this.cache.put(block.getBlockID(), block);	
		}
	}

	private int findClientWithOldestBlock(CacheBlock forwardingBlock) {	
		//double txTime = 0.0;
		List <CacheBlock> listofOldestBlocks = new ArrayList<CacheBlock>();
		
		for(int i=0; i<FileSystem.getNumberOfClients(); i++) 
		{
			if(i==this.clientID)
				continue;
			
//			txTime += 0.00001;
//			final Client client = FileSystem.setOfClient[i];
//			Simulate.sim.doAfter (txTime, new Event()
//			{
//				public void perform()
//				{
//					System.out.println("Hello inside perform");
			CacheBlock forwardingBlockFromOtherClient = FileSystem.setOfClient[i].getForwardingBlock();
			
			if(forwardingBlockFromOtherClient!=null)
			{
				forwardingBlockFromOtherClient.setHoldingClient(i);
				listofOldestBlocks.add(forwardingBlockFromOtherClient);
			}					
			//System.out.println("listofoldestblock " + listofOldestBlocks.toString() +" was called at "+ Simulate.sim.time());
//				}
//			});			
		}
		
		CacheBlock oldestBlock = forwardingBlock;
		int victimClient = this.clientID;
		
		if(listofOldestBlocks != null)	
			for (CacheBlock forwardingBlockFromOtherClient : listofOldestBlocks) {
				if(forwardingBlockFromOtherClient.getAccessTime() < oldestBlock.getAccessTime())
				{
					oldestBlock = forwardingBlockFromOtherClient;
					victimClient = forwardingBlockFromOtherClient.getHoldingClient();
				}
			}
		//System.out.println("Victim Client is "+ FileSystem.setOfClient[victimClient] + " with block " + oldestBlock);
		return victimClient;
	}
	
	public CacheBlock getForwardingBlock() {	
		//System.out.println("Client " + this.clientID +" was called at "+Simulate.sim.time());
		Map.Entry<Integer, CacheBlock> entry = null;
		
		if(super.cache.size() != 0)
			entry = super.cache.entrySet().iterator().next();
		
		CacheBlock forwardingBlock = null;
		if(entry!=null)
			forwardingBlock = new CacheBlock(entry.getValue());
		
		return forwardingBlock;
	}
	
	public CacheBlock removeForwardingBlock() {	
		//System.out.println("Client " + this.clientID +" was called at "+Simulate.sim.time());
		Map.Entry<Integer, CacheBlock> entry = super.cache.entrySet().iterator().next();
		
		CacheBlock forwardingBlock = null;
		if(entry!=null)
			forwardingBlock = super.cache.remove(entry.getKey());
		
		return forwardingBlock;
	}
	
	public void forwardBlock(CacheBlock forwardedBlock) {
		//System.out.println("Forwarded block "+forwardedBlock + " to client "+ this);
		CacheBlock discardingCacheBlock= null;
		if(cacheFull())
		{
			discardingCacheBlock = this.removeForwardingBlock();
			if(discardingCacheBlock.IsMasterBlock())
				super.removeHints(discardingCacheBlock.getBlockID());
		}
		forwardedBlock.setAccessTime(Simulate.sim.time());
		this.cache.put(forwardedBlock.getBlockID(), forwardedBlock);
		super.updateHints(forwardedBlock.getBlockID(), this.clientID);
	}
	

	public String toString() {
		return("Hint-Based "+ super.toString());
	}	
}
