import java.util.Hashtable;

//******************************************************************************
//
// File:    Manager.java
// Package: 
// Unit:    Class Manager
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

public class Manager {
	
	public Hashtable <Integer,Integer> hints;
	
	public Manager() {
		System.out.println("Manager was created");
		hints = new Hashtable<Integer, Integer> ();
	}
	
	public Boolean containsHint(int requestBlockID) {
		return hints.containsKey(requestBlockID);
	}
	
	public String toString() {
		return "Manager" ;
	}

	public int getHint(int requestBlockID) {
		return hints.get(requestBlockID);
	}

	public void updateHintsOfManager(int blockID, int clientID) {
		this.hints.put(blockID, clientID);
	}

	public void removeHintsOfManager(int blockID) {
		this.hints.remove(blockID);
	}
	
}
