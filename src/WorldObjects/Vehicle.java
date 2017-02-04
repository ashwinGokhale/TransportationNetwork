package WorldObjects;

import Graph.Location;
import Main.Visualize;

import java.util.ArrayList;

/**
 * @author Ashwin Gokhale.
 */
public class Vehicle{
	static int numMade = 0;
	static ArrayList<Vehicle> vehicles = new ArrayList<>();
	private int speedOfTravel;
	private String serviceState;
	private double unitCost;
	private int typeOfCost;
	private int waitTime;
	private Location currentLocation;

	public Vehicle(String currentLocation, String serviceState, int speedOfTravel, int waitTime, double unitCost, int typeOfCost) {
		this.speedOfTravel = speedOfTravel;
		this.waitTime = waitTime;
		this.currentLocation = Visualize.g.getGraph().get(currentLocation);
		this.serviceState = serviceState;
		this.unitCost = unitCost;
		this.typeOfCost = typeOfCost;
		vehicles.add(this);
		numMade++;
	}

	public int getSpeedOfTravel() {
		return speedOfTravel;
	}

	public String getServiceState() {
		return serviceState;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public int getTypeOfCost() {
		return typeOfCost;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public String toString() {
		return "Current Location: " + getCurrentLocation();
	}

	public double calculateCostOfTravel(double unitCost, int typeOfCost, double distance){
		// typeOfCost: 1 is fixed (regardless of time or distance), 2 is fixed + a factor of distance  (e.g. a Taxi cab),
		// and 3 is based on fraction of distance (e.g. a Car where unitCost is expressed as cost/gallon)

		switch (typeOfCost){
			case 1:
				return unitCost;

			case 2:
				return unitCost + (distance * 3);

			case 3:
				return distance / (30 * unitCost);

			default:
				return 0.0;
		}
	}
}
