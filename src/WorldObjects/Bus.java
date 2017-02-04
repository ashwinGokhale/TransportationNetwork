package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Bus extends Vehicle{

	// costOfTravel = fixed cost
	public Bus(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 30, 10, 0.5, 1);
	}
}
