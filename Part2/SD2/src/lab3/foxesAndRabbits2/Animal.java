package lab3.foxesAndRabbits2;

import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public abstract class Animal
{

    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The animal's age
    private int age;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Return the animal's age.
     * @return age of animal.
     */
    public int getAge()
    {
    	return age;
    }
    
    /**
     * Set the age of the animal
     * @param newAge The age of the animal
     */
    public void setAge(int newAge)
    {
    	age = newAge;
    }
    
    /**
     * An animal can breed if it has reached it's breeding age.
     * @param breedAge The age in which the animal can breed.
     */
    public boolean canBreed()
    {
    	return age >= getBreedingAge();
    }
    
    /**
     * Increment the animal's age by one year.
     */
    public void incrementAge()
    {
    	age++;
    	if (age > getMaxAge()) {
    		setDead();
    	}
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    public int breed()
    {
    	int births = 0;
    	if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
    		births = rand.nextInt(getMaxLitterSize()) + 1;
    	}
    	return births;
    }
    
    
    // ABSTRACT METHODS //
    
    /**
     * Get breeding age for the animal
     * @return Breeding age.
     */
    public abstract int getBreedingAge();
    
    /**
     * Get maximum age an animal can be.
     * @return Max age.
     */
    public abstract int getMaxAge();
    
    /**
     * Get breeding probability.
     * @return Double breeding probability
     */
    public abstract double getBreedingProbability();
    
    /**
     * Get maximum litter size of the animal.
     * @return Max litter size.
     */
    public abstract int getMaxLitterSize();
}
