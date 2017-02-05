package WorldObjects;

import Graph.Location;

import java.util.ArrayList;

/**
 * @author Ashwin Gokhale.
 */
public class Passenger{
	private String name;
	private Location currentLoc;
	private Location dest;
	private int preference;
	private String vehiclePreference;
	private ArrayList<Location> path;

	public Passenger(String name, Location currentLoc, Location dest, int preference, String vehiclePreference) {
		this.name = name;
		this.currentLoc = currentLoc;
		this.dest = dest;
		this.preference = preference;   // 1 for shortest time, 2 for lowest cost
		this.vehiclePreference = vehiclePreference;
	}

	public String getName() {
		return name;
	}

	public Location getCurrentLoc() {
		return currentLoc;
	}

	public Location getDest() {
		return dest;
	}

	public int getPreference() {
		return preference;
	}

	public String getVehiclePreference() {
		return vehiclePreference;
	}

	public ArrayList<Location> getPath() {
		return path;
	}

	public void setPath(ArrayList<Location> path) {
		this.path = path;
	}
}
