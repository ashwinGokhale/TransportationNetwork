package Main;

import Graph.Edge;
import Graph.Graph;
import Graph.Location;
import WorldObjects.Passenger;
import WorldObjects.Vehicle;
import processing.core.PApplet;
import processing.core.PImage;
import settings.SettingsParser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Ashwin Gokhale.
 */
public class Visualize extends PApplet{
	public static Graph g;
	public static ArrayList<Location> locations = new ArrayList<>();
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	private PImage bg;
	private float x;
	private float y;
	private int h = 20;
	private int passengerIndex = 0;
	private int locIndex = 1;
	private int sizeX = 1700;
	private int sizeY = 900;
	private ArrayList<Location> path;
	private ArrayList<TextLabel> labels = new ArrayList<>();

	private Visualize(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public static void main(String args[]) {
		Visualize v = new Visualize(1700, 900);
		v.runSketch();
	}

	public void settings(){
		size(sizeX, sizeY, JAVA2D);
		smooth();

	}

	public void setup() {
		URL url = getClass().getResource("Map.PNG");
		File file = new File(url.getPath());

		bg = loadImage(file.getAbsolutePath());
		g = new Graph();
		SettingsParser.loadVehicles();
		SettingsParser.loadLocations();
		SettingsParser.createPaths();
		SettingsParser.loadPassengers();

		for (Passenger p : passengers) {
			g.computePaths(p);
			ArrayList<Location> personPath = Graph.getShortestPathTo(p.getDest());
			if (personPath.get(0).equals(personPath.get(1)))
				p.setPath(new ArrayList<>(Arrays.asList(p.getCurrentLoc(), p.getCurrentLoc())));
			else
				p.setPath(personPath);
			System.out.printf("%s path: %s Using: %s   Total Cost: $%.2f\n", p.getName(), p.getPath(), p.getVehiclePreference(), p.calculateCost());
		}

		background(255);

		path = passengers.get(passengerIndex).getPath();
		x = path.get(0).getX();
		y = path.get(0).getY();
	}

	public void draw(){
		image(bg, 0, 0);
		frameRate(30);

		// Map all locations
		for (Location x : locations){
			fill(255, 0, 0);
			ellipse(x.getX(), x.getY(), 20,20);

			// San Ramon and Dublin are not on the Google Maps image
			if (x.getName().equals("San Ramon") || x.getName().equals("Dublin")) {
				fill(0);
				textSize(13);
				text(x.getName(), x.getX() - 30, x.getY() - 20);
			}

			// For each adjacent location, draw a line between them
			for(Vehicle vehicle : x.getVehicles()){
				if (vehicle != null) {
					for (Edge e : x.getAdjacent().get(vehicle))
						line(x.getX(), x.getY(), e.to.getX(), e.to.getY());
				}
			}
		}

		// Linearly interpolate the person's position and their next destination
		x = lerp(x, path.get(locIndex).getX(), 0.1f);
		y = lerp(y, path.get(locIndex).getY(), 0.1f);

		// Create a circle that represents the current passenger as they traverse the graph and add their name above it
		fill(0,175,255);
		ellipse(x,y,25,25);
		fill(0);
		text(passengers.get(passengerIndex).getName(), x-10, y);

		// Once a passenger arrives at their next location, if they arrive at their destination, go to the next person, otherwise, go to next location
		if (Math.abs(x - path.get(locIndex).getX()) < 1 || Math.abs(y - path.get(locIndex).getY()) < 1){
			boolean changedPaths = false;

			// If passenger has reached their destination
			if (locIndex + 1 == path.size()) {
				// If last passenger has reached their destination
				if (passengerIndex + 1 == passengers.size()) {
					System.out.println("FINISHED!");
					// Print the last person's name at their finishing position
					fill(60, 0, 110);
					textSize(20);
					text(passengers.get(passengerIndex).getName(), x-10, y-20);

					// Print all passenger's paths in the white space to the right
					for (Passenger p : passengers){

						// If passenger could not find a path to their destination, their path would be: [currentLoc, currentLoc]
						if (p.getPath().get(0).equals(p.getPath().get(1))) {
							text(String.format("No possible path for %s to go from %s to %s using a %s\n", p.getName(), p.getCurrentLoc(), p.getDest(), p.getVehiclePreference()), 720, h);
						}
						else
							text(String.format("%s path: %s Using: %s   Total Cost: $%.2f\n", p.getName(),p.getPath(), p.getVehiclePreference(), p.calculateCost()), 720,h);
						h += 20;
					}


					// Stop drawing
					noLoop();
				}

				// If passenger has reached their destination, get next passenger
				else {
					labels.add(new TextLabel(passengers.get(passengerIndex).getName(), x-10, y-20));
					passengerIndex++;
					locIndex = 1;
					changedPaths = true;
				}
			}

			// Otherwise, continue to the next location
			else
				locIndex++;

			path = passengers.get(passengerIndex).getPath();

			// If passenger has changed, set x and y to the coordinates of their current location
			if (changedPaths) {
				x = path.get(0).getX();
				y = path.get(0).getY();
			}
		}

		// Once a passenger has arrived,
		for (TextLabel label : labels) {
			fill(60, 0, 110);
			textSize(20);
			text(label.name, label.x, label.y);
		}
	}

	public void mouseClicked() {
		System.out.printf("(%d, %d)\n",mouseX, mouseY);
	}
}

