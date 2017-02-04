package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Aircraft extends Vehicle{

	// costOfTravel = fixed cost
	public Aircraft(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 575, 120, 400, 1);
	}
}
