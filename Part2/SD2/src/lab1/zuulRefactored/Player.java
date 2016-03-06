package lab1.zuulRefactored;

import java.util.HashSet;

public class Player {
	
	Room currentRoom;
	String pName;
	HashSet<Item> items;
	int weight;
	final int MAXWEIGHT = 30;

	public Player(String name) {
		pName = name;
		items = new HashSet<Item>();
		weight = 0;
	}
	
	public String getPlayerName() {
		return pName;
	}
	
	public Room getPlayerRoom() {
		return currentRoom;
	}
	
	public void setPlayerRoom(Room room) {
		currentRoom = room;
	}
	
	// Return true if item picked up, false otherwise
	public boolean pickUpItem(Item i) {
		if ((weight + i.getWeight() <= MAXWEIGHT)) {
			weight += i.getWeight();
			items.add(i);
			return true; 
		} else { 
			return false;
		}
	}
	
	public HashSet<Item> getItems() {
		return items;
	}
	
	public void dropItem(Item i) {
		weight -= i.getWeight();
		items.remove(i);
	}
	
	public int getWeight() {
		return weight;
	}
}
