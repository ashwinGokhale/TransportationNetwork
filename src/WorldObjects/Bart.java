package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Bart extends Vehicle{

	// costOfTravel = fixed cost
	public Bart(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 60, 10, 1.5, 1);
	}
}
