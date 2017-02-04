package Graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ashwin on 1/28/2017.
 */
public class Location implements Comparable<Location>{
	private String name;
	private int x;
	private int y;
	double dist = Double.POSITIVE_INFINITY;
	Location prev;
	private boolean[] vehicleTypes; // Aircraft, Bart, Bicycle, Bus, Car, Taxi
	private HashMap<String, ArrayList<Edge>> adjacent;  // Maps Vehicle type to ArrayList of adjacent Locations as Edge objects
	public static String[] vehicleTypeList = new String[]{"Aircraft", "Bart", "Bicycle", "Bus", "Car", "Taxi"};

	public Location(String name, int x, int y  , boolean[] vehicleTypes) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.vehicleTypes = vehicleTypes;

		// Initialize Adjacency HashMap
		this.adjacent = new HashMap<>();
		for (String vehicle : vehicleTypeList) adjacent.put(vehicle, new ArrayList<>());

	}

	String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean[] getVehicleTypes() {return vehicleTypes;}


	public HashMap<String, ArrayList<Edge>> getAdjacent() {return adjacent;}

	void addNeighbor(Location neighbor, double weight, String vehicleType){
		Edge e = new Edge(this, neighbor, weight , vehicleType);
		if (!adjacent.get(vehicleType).contains(e))
			adjacent.get(vehicleType).add(e);
	}

	public static double getDistance(Location loc1, Location loc2){
		return Math.sqrt(Math.pow(loc2.getX() - loc1.getX(), 2) + Math.pow(loc2.getY() - loc1.getY(), 2));
	}

	public int compareTo(Location other)
	{
		return Double.compare(dist, other.dist);
	}

	public String toString() {
		return String.format("%s at (%d,%d)", getName(), getX(), getY());
	}
}
