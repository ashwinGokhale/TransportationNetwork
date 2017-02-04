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
			g.computePaths(p.getCurrentLoc());
			System.out.printf("%s went on this path: %s\n", p.getName(),Graph.getShortestPathTo(p.getDest()));
		}


	}

	public void draw(){
		background(bg);
		ellipse(mouseX, mouseY, 20, 20);
		for (Location x : locations){
			fill(255, 0, 0);
			ellipse(x.getX(), x.getY(), 20,20);

			for(Edge e : x.getAdjacent()){
				line(x.getX(), x.getY(), e.to.getX(), e.to.getY());
			}
		}


	}

	public void mouseClicked() {
		System.out.printf("(%d, %d)",mouseX, mouseY);
	}

}