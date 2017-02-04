package Graph;

/**
 * Created by Ashwin on 2/3/2017.
 */
public class Edge {
	private Location from;
	public Location to;
	double weight;
	private String vehicleType;

	Edge(Location from, Location to, double weight, String vehicleType) {
		this.from = from;
		this.to = to;
		this.weight = weight;
		this.vehicleType = vehicleType;
	}

	public String toString() {
		return String.format("(%s -> %s):\tDistance: %.02f\tVehicle Type: %s\n", from.getName(), to.getName(), weight, vehicleType);
	}
}
