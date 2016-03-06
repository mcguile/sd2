/**
 * 
 */
package lab1.zuulRefactored;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author vrieser
 *
 */
public class BackTest
{

	/**
     * Default constructor for test class SalesItemTest
     */
    public BackTest()
    {
    }

	@Test
	public void testBack()
	{
		// Init Game and begin play
		Game game = new Game();
		Room start= game.getCurrentRoom();
		
		// Call goRoom method with valid commands to move to a room
		Command command = new Command("go", "east");
		game.goRoom( command );
		
		// Validate that the new room is not the start room
		assertTrue(!start.equals( game.getCurrentRoom()));
		
		Command back = new Command("back", null);
		
		// Validate moving back to previous room
		game.goBack(back );
		assertTrue(start.equals( game.getCurrentRoom()));
		
	}
	
	
	
	
}