package lab3.foxesAndRabbits2;

import java.util.List;
import java.util.Random;

import lab3.foxesAndRabbits2.Fox;
import lab3.foxesAndRabbits2.Location;
import lab3.foxesAndRabbits2.Rabbit;
import lab3.foxesAndRabbits2.Randomizer;

public class PopulationGenerator {
	
	// The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;  
    // The probability that a rabbit will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.1;
	
	private Field field;
	private List<Animal> animals;
	
	/**
	 * Generate the population of animals.
	 * 
	 * @param field The field currently occupied.
	 * @param animals The animals in the field.
	 */
	public PopulationGenerator(Field field, List<Animal> animals)
	{
		this.field = field;
		this.animals = animals;
	}
	
	/**
	 * Return the field in which the animals are.
	 * 
	 * @return Field object.
	 */
	public Field getField()
	{
		return field;
	}
	
	/**
	 * Return the list of animals in the field.
	 * 
	 * @return List<Animal> animals.
	 */
	public List<Animal> getAnimals()
	{
		return animals;
	}
	
	/**
     * Randomly populate the field with foxes and rabbits.
     */
    public void populate()
    {
    	Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    animals.add(rabbit);
                }
                else if(rand.nextDouble() <= WOLF_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Wolf wolf = new Wolf(true, field, location);
                    animals.add(wolf);
                }
                // else leave the location empty.
            }
        }
    }
}