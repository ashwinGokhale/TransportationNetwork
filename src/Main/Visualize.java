package Main;

import Graph.*;
import WorldObjects.*;
import processing.core.*;
import settings.SettingsParser;

import java.util.ArrayList;

/**
 * @author Ashwin Gokhale.
 */
public class Visualize extends PApplet {
	PImage bg;
	public static Graph g;
	public static ArrayList<Location> locations = new ArrayList<>();
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	float x;
	float y;
	int passengerIndex = 0;
	int locIndex = 1;
	ArrayList<Location> path;
	ArrayList<TextLabel> labels = new ArrayList<>();

	public static void main(String args[]) {
		PApplet.main("Main.Visualize");
	}

	public void settings(){
		size(716, 900);
	}

	public void setup() {
		bg = loadImage("Main/Map.png");
		g = new Graph();
		SettingsParser.loadLocations();
		SettingsParser.createPaths();
		SettingsParser.loadPassengers();
		SettingsParser.loadVehicles();

		for (Passenger p : passengers) {
			g.computePaths(p);
			ArrayList<Location> personPath = Graph.getShortestPathTo(p.getDest());
			System.out.printf("%s went on this path: %s\n", p.getName(),personPath);
			p.setPath(personPath);
		}

		smooth();

		path = passengers.get(passengerIndex).getPath();
		x = path.get(0).getX();
		y = path.get(0).getY();

	}



	public void draw(){
		background(bg);
		ellipse(mouseX, mouseY, 20, 20);

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
			for(String vehicle : Location.vehicleTypeList){
				for (Edge e : x.getAdjacent().get(vehicle))
					line(x.getX(), x.getY(), e.to.getX(), e.to.getY());
			}
		}

		x = lerp(x, path.get(locIndex).getX(), 0.04f);
		y = lerp(y, path.get(locIndex).getY(), 0.04f);
		fill(0,175,255);
		ellipse(x,y,25,25);
		fill(0);
		text(passengers.get(passengerIndex).getName(), x-10, y);

		if (Math.abs(x - path.get(locIndex).getX()) < 1 || Math.abs(y - path.get(locIndex).getY()) < 1){
			boolean changedPaths = false;

			if (locIndex + 1 == path.size()) {
				if (passengerIndex + 1 == passengers.size()) {
					System.out.println("FINISHED!");
					noLoop();
				}

				else {
					labels.add(new TextLabel(passengers.get(passengerIndex).getName(), x-10, y-20));
					passengerIndex++;
					locIndex = 1;
					changedPaths = true;
				}
			}

			else
				locIndex++;


			path = passengers.get(passengerIndex).getPath();

			if (changedPaths) {
				x = path.get(0).getX();
				y = path.get(0).getY();
			}
		}

		for (TextLabel label : labels) {
			fill(60, 0, 110);
			textSize(20);
			text(label.name, label.x, label.y);
		}
	}

	public void mouseClicked() {
		System.out.printf("(%d, %d)",mouseX, mouseY);
	}

}