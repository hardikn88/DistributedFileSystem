import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class LACClient extends Client {

	private Map<Integer, MetaDataofBlock> LRU_Stack;
	private Map<Integer, MetaDataofBlock> forwardingCandidatePool;
	private int forwardingCandidatePoolCapacity;
	private int epochCounter, epochTimer;
	
	public LACClient(int clientID, final int cacheSize, final int forwardingPoolSize, int epochCounter, int epochTimer, Boolean fillCache) {
		super(clientID, cacheSize, cacheSize-forwardingPoolSize, fillCache);
		this.forwardingCandidatePoolCapacity = forwardingPoolSize;
		this.LRU_Stack = Collections.synchronizedMap(new LinkedHashMap<Integer, MetaDataofBlock>(cacheSize, 1.1f, true));
		this.forwardingCandidatePool = Collections.synchronizedMap(new LinkedHashMap<Integer, MetaDataofBlock>(forwardingPoolSize, 1.1f, true));
		this.epochCounter = epochCounter;
		this.epochTimer = epochTimer;
	}

	@Override
	public void Eviction(CacheBlock block) {
		MetaDataofBlock oldestCachedBlock = null, accessedBlock = null;
		
		if(metaDataPresentInLRUStack(block.getBlockID()))
			accessedBlock = getMetaDataFromLRUStack(block.getBlockID());
		else
			accessedBlock = new MetaDataofBlock(block.getBlockID());
		
		if(cacheFull())
		{			
			if (!accessedBlock.getBlockType().equals(BlockType.CACHEDBLOCK))
			{
				oldestCachedBlock = fetchMetaDataofCachedBlockWithLargestRecency(); 
				if(accessedBlock.getBlockType().equals(BlockType.FORWARDINGBLOCK))
				{
					if(accessedBlock.getRecency() > oldestCachedBlock.getRecency())
					{
						removeMetaDataFromForwardingCandidatePool(accessedBlock);
						accessedBlock.setBlockType(BlockType.CACHEDBLOCK);
						oldestCachedBlock.setBlockType(BlockType.FORWARDINGBLOCK);
						addMetaDataToForwardingCandidatePool(oldestCachedBlock);
						addMetaDataToLRUStack(oldestCachedBlock);
					}
					else
						accessedBlock = forwardingCandidatePool.get(accessedBlock.getBlockID());		
				}
				else //if block is a shadow or first time accessed block
				{
					if(forwardingCandidateFull())
						forwarding();
					
					if(accessedBlock.getRecency() > oldestCachedBlock.getRecency())
					{
						accessedBlock.setBlockType(BlockType.CACHEDBLOCK);
						oldestCachedBlock.setBlockType(BlockType.FORWARDINGBLOCK);
						addMetaDataToForwardingCandidatePool(oldestCachedBlock);
						addMetaDataToLRUStack(oldestCachedBlock);
					}
					else
					{
						accessedBlock.setBlockType(BlockType.FORWARDINGBLOCK);
						addMetaDataToForwardingCandidatePool(accessedBlock);
					}
				}
			}		
		}
		else
		{
			accessedBlock.setBlockType(BlockType.CACHEDBLOCK);
		}
		
		if(accessedBlock != null)
		{
			accessedBlock.setRecency(Simulate.sim.time());
			accessedBlock.setEpochCounter(this.epochCounter);
			this.epochTimer--;
			//System.out.println("Epoch Timer at " + this + " is " + epochTimer);
			
			if(this.epochTimer == 0)
			{
				this.epochCounter++;
				this.resetEpochTimer();
				for(int i=0; i<FileSystem.getNumberOfClients(); i++) 
				{
					if(i == this.clientID )
						continue;
					((LACClient) FileSystem.setOfClient[i]).updateEpochCounter(this.epochCounter);
					((LACClient) FileSystem.setOfClient[i]).resetEpochTimer();
				}
			}
			addMetaDataToLRUStack(accessedBlock);
		}
		
		System.out.println("LRU Stack Block " + accessedBlock);
		System.out.println("Forwarding Pool : " + forwardingCandidatePool.toString()+ " of client: " + this.toString());
		System.out.println("LRU Stack : " + LRU_Stack.toString()+" of client : " + this.toString() );
		this.cache.put(block.getBlockID(), block);
	}

	private void forwarding() {
		System.out.println("Forwarding Candidate is : "+ forwardingCandidatePool.entrySet().iterator().next().getValue());
		CacheBlock forwardingBlock= this.removeForwardingBlock();
		System.out.println("Forwarding Block is : "+ forwardingBlock);
		MetaDataofBlock forwardingMetaDataofBlock = this.removeFromForwardingCandidatePool();
		int victimClient = findClientWithLeastUtilizationValue(forwardingMetaDataofBlock);
		if(victimClient != this.clientID)
		{
			FileSystem.setOfClient[victimClient].forwardBlock(forwardingBlock);
			if(forwardingBlock.IsMasterBlock())
			{
				super.updateHints(forwardingBlock.getBlockID(), victimClient);
				FileSystem.manager.updateHintsOfManager(forwardingBlock.getBlockID(), victimClient);
			}
		}
		else
			if(forwardingBlock.IsMasterBlock())
				super.removeHints(forwardingBlock.getBlockID());
	}
	
	private int findClientWithLeastUtilizationValue(MetaDataofBlock forwardingMetaDataofBlock) {
		
		List <MetaDataofBlock> listofMetaDataofOldestCachedBlocks = new ArrayList<MetaDataofBlock>();
		
		for(int i=0; i<FileSystem.getNumberOfClients(); i++) 
		{
			if(i==this.clientID)
				continue;
			
			MetaDataofBlock leastUtilizedCachedBlockFromOtherClient = ((LACClient) FileSystem.setOfClient[i]).fetchMetaDataofCachedBlockWithLargestRecency();
			
			if(leastUtilizedCachedBlockFromOtherClient!=null)
			{
				leastUtilizedCachedBlockFromOtherClient.setHoldingClient(i);
				listofMetaDataofOldestCachedBlocks.add(leastUtilizedCachedBlockFromOtherClient);
			}
		}
				
		//MetaDataofBlock leastUtilizedCachedBlock = forwardingMetaDataofBlock;
		MetaDataofBlock leastUtilizedCachedBlock = this.fetchMetaDataofCachedBlockWithLargestRecency();
		int victimClient = this.clientID;
		
		if(listofMetaDataofOldestCachedBlocks!=null)
			for (MetaDataofBlock leastUtilizedCachedBlockFromOtherClient : listofMetaDataofOldestCachedBlocks) {
				if(leastUtilizedCachedBlockFromOtherClient.getEpochCounter() < leastUtilizedCachedBlock.getEpochCounter())
				{
					leastUtilizedCachedBlock = leastUtilizedCachedBlockFromOtherClient;
					victimClient = leastUtilizedCachedBlockFromOtherClient.getHoldingClient();
				}
			}
		
		if(victimClient != this.clientID) 
		{
			if(forwardingMetaDataofBlock.getEpochCounter() <= leastUtilizedCachedBlock.getEpochCounter())
				victimClient = this.clientID;
		}
		
		System.out.println("Victim Client is " + victimClient + " with MetaBlock " + forwardingMetaDataofBlock+" or CachedBlock "+ leastUtilizedCachedBlock);
		return victimClient;
	}

	private boolean forwardingCandidateFull() {
		return this.forwardingCandidatePool.size() >= forwardingCandidatePoolCapacity;
	}

	private void addMetaDataToForwardingCandidatePool(
			MetaDataofBlock block) {
		if(block != null)
			forwardingCandidatePool.put(block.getBlockID(), block);
	}

	private void removeMetaDataFromForwardingCandidatePool(
			MetaDataofBlock block) {
		if(forwardingCandidatePool.containsKey(block.getBlockID()))
			forwardingCandidatePool.remove(block.getBlockID());
	}

	public MetaDataofBlock fetchMetaDataofCachedBlockWithLargestRecency() {
		Iterator<Map.Entry<Integer, MetaDataofBlock>> entries = LRU_Stack.entrySet().iterator();
		MetaDataofBlock block = null;
		
		while (entries.hasNext())
		{
			Map.Entry<Integer, MetaDataofBlock> entry = entries.next();
			if(entry.getValue().getBlockType().equals(BlockType.CACHEDBLOCK)) {
				block = new MetaDataofBlock(entry.getValue());
				break;
			}
		}		
		return block;
	}

	private void addMetaDataToLRUStack(MetaDataofBlock block) {
		if(block != null)
			LRU_Stack.put(block.getBlockID(), block);		
	}

	private Boolean metaDataPresentInLRUStack(int blockID) {
		return LRU_Stack.containsKey(blockID);
	}

	private MetaDataofBlock getMetaDataFromLRUStack(int blockID) {
		return new MetaDataofBlock(LRU_Stack.get(blockID));
	}

	@Override
	public CacheBlock getForwardingBlock() {
		return null;
	}
	
	@Override
	public CacheBlock removeForwardingBlock() {
		MetaDataofBlock forwardingMetaData = forwardingCandidatePool.entrySet().iterator().next().getValue();
		CacheBlock forwardingBlock =  super.cache.remove(forwardingMetaData.getBlockID());
		return forwardingBlock;
	}
	
	@Override
	public void forwardBlock(CacheBlock forwardedBlock) {
		MetaDataofBlock metaDataofDiscardingCacheBlock = null, metaDataofForwardedBlock = null;
		CacheBlock discardingCacheBlock = null;
		
		System.out.println("Forwarded block "+forwardedBlock +" is Master Block "+ forwardedBlock.IsMasterBlock() + " to client "+ this +" with LRU Stack \n"+ LRU_Stack.toString());
		
		if(metaDataPresentInLRUStack(forwardedBlock.getBlockID()))
			metaDataofForwardedBlock = getMetaDataFromLRUStack(forwardedBlock.getBlockID());
		else
			metaDataofForwardedBlock = new MetaDataofBlock(forwardedBlock.getBlockID());
		
		if(cacheFull())
		{			
			if (!metaDataofForwardedBlock.getBlockType().equals(BlockType.CACHEDBLOCK))
			{
				metaDataofDiscardingCacheBlock = fetchMetaDataofCachedBlockWithLargestRecency();
				discardingCacheBlock = super.cache.remove(metaDataofDiscardingCacheBlock.getBlockID());
				metaDataofDiscardingCacheBlock.setBlockType(BlockType.SHADOWBLOCK);
				addMetaDataToLRUStack(metaDataofDiscardingCacheBlock);
				if(discardingCacheBlock.IsMasterBlock())
					super.removeHints(discardingCacheBlock.getBlockID());
			}
		}
		
		if(metaDataofForwardedBlock != null)
		{
			forwardingCandidatePool.remove(metaDataofForwardedBlock.getBlockID());
			metaDataofForwardedBlock.setBlockType(BlockType.CACHEDBLOCK);
			metaDataofForwardedBlock.setRecency(Simulate.sim.time());
			metaDataofForwardedBlock.setEpochCounter(this.epochCounter);
			addMetaDataToLRUStack(metaDataofForwardedBlock);
		}
		
		if(forwardedBlock.IsMasterBlock())
		{
			this.cache.put(forwardedBlock.getBlockID(), forwardedBlock);
			this.updateHints(forwardedBlock.getBlockID(), this.clientID);
		}
		else
			if(!this.cache.containsKey(forwardedBlock.getBlockID()))
				this.cache.put(forwardedBlock.getBlockID(), forwardedBlock);
		
		System.out.println("After adding block TO LRU stack " + LRU_Stack.toString());
	}
	
	private MetaDataofBlock removeFromForwardingCandidatePool() {
		MetaDataofBlock forwardingCandidate = forwardingCandidatePool.entrySet().iterator().next().getValue();
		forwardingCandidate = forwardingCandidatePool.remove(forwardingCandidate.getBlockID());
		forwardingCandidate.setBlockType(BlockType.SHADOWBLOCK);
		addMetaDataToLRUStack(forwardingCandidate);
		return forwardingCandidate;
	}
	
	public void updateEpochCounter(int epochCounter) {
			this.epochCounter = epochCounter;
	}
	
	public void resetEpochTimer() {
		this.epochTimer = ConfigReader.getEpochTimer();
	}

	public String toString() {
		return("LAC-Based "+ super.toString());
	}
}
