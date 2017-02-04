package WorldObjects;

/**
 * @author Ashwin Gokhale.
 */
public class Taxi extends Vehicle{

	// costOfTravel = fare by distance
	public Taxi(String currentLocation, String serviceState) {
		super(currentLocation, serviceState, 50, 10, 3.5, 2);
	}
}
