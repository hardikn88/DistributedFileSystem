import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
	
	public LACClient(int clientID, final int cacheSize, final int forwardingPoolSize) {
		super(clientID, cacheSize, cacheSize-forwardingPoolSize);
		this.forwardingCandidatePoolCapacity = forwardingPoolSize;
		this.LRU_Stack = Collections.synchronizedMap(new LinkedHashMap<Integer, MetaDataofBlock>(cacheSize, 1.1f, true));
		this.forwardingCandidatePool = Collections.synchronizedMap(new LinkedHashMap<Integer, MetaDataofBlock>(forwardingPoolSize, 1.1f, true));
	}

	@Override
	public void Eviction(CacheBlock block) {
		MetaDataofBlock oldestCachedBlock, accessedBlock = null; 
		if(cacheFull())
		{
			if(metaDataPresentInLRUStack(block.getBlockID()))
				accessedBlock = getMetaDataFromLRUStack(block.getBlockID());
			else
				accessedBlock = new MetaDataofBlock(block.getBlockID());
			
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
			if(metaDataPresentInLRUStack(block.getBlockID()))
				accessedBlock = getMetaDataFromLRUStack(block.getBlockID());
			else
			{
				accessedBlock = new MetaDataofBlock(block.getBlockID());
				accessedBlock.setBlockType(BlockType.CACHEDBLOCK);
			}
		}
		
		if(accessedBlock != null)
		{
			accessedBlock.setRecency(Simulate.sim.time());
			addMetaDataToLRUStack(accessedBlock);
		}
		
		System.out.println("LRU Stack Block " + accessedBlock);
		System.out.println("Forwarding Pool : " + forwardingCandidatePool.toString()+ " of client: " + this.toString());
		System.out.println("LRU Stack : " + LRU_Stack.toString()+" of client : " + this.toString() );
		this.cache.put(block.getBlockID(), block);
	}

	private void forwarding() {
		System.out.println("Forwarding Candidate is : "+ forwardingCandidatePool.entrySet().iterator().next().getValue());
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

	private MetaDataofBlock fetchMetaDataofCachedBlockWithLargestRecency() {
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

	private void addMetaDataToLRUStack(MetaDataofBlock accessedBlock) {
		if(accessedBlock != null)
			LRU_Stack.put(accessedBlock.getBlockID(), accessedBlock);
		
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
		return null;
	}

	
	@Override
	public void forwardBlock(CacheBlock forwardedBlock) {
		
	}
	
	public String toString() {
		return("LAC-Based "+ super.toString());
	}
}
