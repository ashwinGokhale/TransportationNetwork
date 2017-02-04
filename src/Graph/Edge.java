package Graph;

/**
 * Created by Ashwin on 2/3/2017.
 */
public class Edge {
	public Location from;
	public Location to;
	public double weight;
	//public String vehicleType;

	public Edge(Location from, Location to, double weight /*,String vehicleType*/) {
		this.from = from;
		this.to = to;
		this.weight = weight;
		//this.vehicleType = vehicleType;
	}

	public String toString() {
		return String.format("(%s -> %s):\tDistance: %.02f\n", from.getName(), to.getName(), weight);
	}
}
