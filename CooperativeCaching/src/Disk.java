/**
 * 
 */

/**
 * @author Hardik
 *
 */
public class Disk {
	public Disk() {
		
	}
	
	public String toString() {
		return "Disk" ;
	}

	public CacheBlock performLookUpInDisk(Client client, int requestBlockID) {
		
		client.blockAccessTime += ConfigReader.getDiskAccessTime();
		client.diskCacheHit++;
		
		CacheBlock block = new CacheBlock(requestBlockID);
		
		synchronized (this) {
			block.setMasterClientHolder(client.clientID);			
		}
		
		return block;
	}
}
