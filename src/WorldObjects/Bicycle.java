package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Bicycle extends Vehicle{

	// costOfTravel = fixed cost
	public Bicycle(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 10, 0, 0, 1);
	}
}
