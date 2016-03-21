package lab3.foxesAndRabbits2;

import java.util.Iterator;
import java.util.List;

public abstract class Predator extends Animal {
	
	
	 // Individual characteristics (instance fields).
    // The wolf's food level, which is increased by eating rabbits.
    private int foodLevel;

	public Predator(Field field, Location location)
	{
		super(field, location);
	}
	
	/**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
	
	public void setFoodLevel(int foodLevel)
	{
		this.foodLevel = foodLevel;
	}
	
	

	public void hunt(List<Animal> newAnimals)
	{
		incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newAnimals);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
	}

	
    abstract public Location findFood();
    abstract public void giveBirth(List<Animal> newAnimals);
}
