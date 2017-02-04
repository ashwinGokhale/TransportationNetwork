package Graph;

import java.util.ArrayList;

/**
 * Created by Ashwin on 1/28/2017.
 */
public class Location implements Comparable<Location>{
	private String name;
	private double lat;
	private double lon;
	private int x;
	private int y;
	public double dist = Double.POSITIVE_INFINITY;
	public Location prev;
	//private boolean[] vehicleTypes; // Aircraft, Bart, Bicycle, Bus, Car, Taxi
	private ArrayList<Edge> adjacent;  // Maps Location to ArrayList containing: 1) Double distance, 2) String vehicleType
	//public static String[] vehicleTypeList = new String[]{"Aircraft", "Bart", "Bicycle", "Bus", "Car", "Taxi"};

	public Location(String name, double lat, double lon, int x, int y  /*, boolean[] vehicleTypes*/) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.x = x;
		this.y = y;
		//this.vehicleTypes = vehicleTypes;
		this.adjacent = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public double getLat() {
		return lat;
	}

	public double getLong() {
		return lon;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// public boolean[] getVehicleTypes() {return vehicleTypes;}

	public ArrayList<Edge> getAdjacent() {
		return adjacent;
	}

	public void addNeighbor(Location neighbor, double weight /*, String vehicleType*/){
		Edge e = new Edge(this, neighbor, weight /*, vehicleType*/);
		if (!adjacent.contains(e))
			adjacent.add(e);
	}

	public static double getGeoDistance(Location loc1, Location loc2){
		double lat1 = loc1.getLat();
		double lat2 = loc2.getLat();
		double lon1 = loc1.getLong();
		double lon2 = loc1.getLong();

		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c * 1000; // convert to meters
	}

	public static double getDistance(Location loc1, Location loc2){
		return Math.sqrt(Math.pow(loc2.getX() - loc1.getX(), 2) + Math.pow(loc2.getY() - loc1.getY(), 2));
	}

	public int compareTo(Location other)
	{
		return Double.compare(dist, other.dist);
	}

	public String toString() {
		return String.format("%s at (%f,%f)", getName(), getLat(), getLong());
	}
}
