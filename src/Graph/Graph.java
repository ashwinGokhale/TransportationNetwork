package Graph;

import java.util.*;

/**
 * Created by Ashwin on 1/28/2017.
 */
public class Graph {
	HashMap<String, Location> graph;
	int numVertices;

	public Graph(){
		this.graph = new HashMap<>();
		this.numVertices = 0;
	}

	public Location addVertex(Location node){
		numVertices++;
		if (!graph.containsKey(node.getName()))
			graph.put(node.getName(), node);

		return node;
	}

	public boolean containsLocation(String node){
		return graph.containsKey(node);
	}

	public void addEdge(Location from, Location to, double weight /*, String vehicleType*/){
		if (!containsLocation(from.getName()))
			addVertex(from);

		if (!containsLocation(to.getName()))
			addVertex(to);

		from.addNeighbor(to, weight /*, vehicleType*/);
		to.addNeighbor(from, weight /*, vehicleType*/);
	}

	public Set<String> getLocationNames(){
		return graph.keySet();
	}

	public HashMap<String, Location> getGraph() {
		return graph;
	}

	public void computePaths(Location source) {

		for (Location x : graph.values()){
			x.dist = Double.POSITIVE_INFINITY;
			x.prev = null;
		}

		source.dist = 0;
		PriorityQueue<Location> vertexQueue = new PriorityQueue<>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Location u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.getAdjacent()) {
				Location v = e.to;
				double alt = u.dist + e.weight;
				if (alt < v.dist) {
					vertexQueue.remove(v);

					v.dist = alt;
					v.prev = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Location> getShortestPathTo (Location target) {
		List<Location> path = new ArrayList<Location>();
		for (Location vertex = target; vertex != null; vertex = vertex.prev)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}


}



