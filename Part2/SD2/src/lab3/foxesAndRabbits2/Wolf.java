package lab3.foxesAndRabbits2;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a wolf.
 * wolves age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Wolf extends Predator
{
    // Characteristics shared by all wolves (class variables).
    
    // The age at which a wolf can start to breed.
    private static final int BREEDING_AGE = 20;
    // The age to which a wolf can live.
    private static final int MAX_AGE = 200;
    // The likelihood of a wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a wolf can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 7;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();


    /**
     * Create a wolf. A wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            setFoodLevel(rand.nextInt(RABBIT_FOOD_VALUE));
        }
        else {
            setAge(0);
            setFoodLevel(RABBIT_FOOD_VALUE);
        }
    }
    
    /**
     * This is what the wolf does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newwolfes A list to return newly born wolves.
     */
    public void act(List<Animal> newWolves)
    {
        hunt(newWolves);
    }
    
    /**
     * Get the breeding age for a wolf.
     * @return Breeding age.
     */
    public int getBreedingAge()
    {
    	return BREEDING_AGE;
    }

    /**
     * Get the maximum age a wolf can be.
     * @return Max age.
     */
    public int getMaxAge()
    {
    	return MAX_AGE;
    }
    
    /**
     * Get the breeding probability for a wolf.
     * @return Double breeding probability.
     */
    public double getBreedingProbability()
    {
    	return BREEDING_PROBABILITY;
    }
    
    /**
     * Get the maximum size of litter for a wolf.
     * @return Max litter size.
     */
    public int getMaxLitterSize()
    {
    	return MAX_LITTER_SIZE;
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    public Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    setFoodLevel(RABBIT_FOOD_VALUE);
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolves A list to return newly born wolves.
     */
    public void giveBirth(List<Animal> newWolves)
    {
        // New wolves are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc);
            newWolves.add(young);
        }
    }
}
