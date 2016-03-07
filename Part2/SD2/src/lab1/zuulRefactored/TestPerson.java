package lab1.zuulRefactored;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

/**
 * @author mcguile
 *
 */
public class TestPerson {
	
	Game game;
	Player player;
	
	/**
     * Default constructor for test class PersonTest
     */
    public TestPerson()
    {
    	game = new Game();
    	player = new Player("Connor");
    	// Begin play with created player
    	game.createPlayer(player);
    }
	
	@Test
	public void testPickup()
	{
		// Get item in the Room (one item)
		HashSet<Item> i = game.getCurrentRoom().getItems();
		// Clone the HashSet so when the item is picked up we do not compare
		// to an empty set
		HashSet<Item> clone = (HashSet<Item>)i.clone();
				
				
		// Call pickup method
		Command pickup = new Command("pickup", "prop");
		game.pickupItem(pickup);
				
		// Compare player's picked up item to clone of item in room
		// Expected: Success
		assertEquals(player.getItems(), clone);
				
		// Check if the item in the room is indeed picked up
		// By Comparing to a new empty HashSet<Item>
		// Expected: no items - Success
		assertEquals(new HashSet<Item>(), game.getCurrentRoom().getItems());
	}

	@Test
	public void testDrop()
	{
		// Create test Item and make player have it
		Item item = new Item("drink", 1);
		player.pickUpItem(item);
		
		// Move to another room to drop the item
		game.goRoom(new Command("go", "east"));
		
		// Drop the item
		game.dropItem(new Command("drop", "drink"));
		
		// Verify item has been dropped
		assertTrue(game.getCurrentRoom().getItems().contains(item));
	}
}
