package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Car extends Vehicle{

	// costOfTravel = distance / unitCost
	public Car(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 50, 0, 2.4, 3);
	}
}
