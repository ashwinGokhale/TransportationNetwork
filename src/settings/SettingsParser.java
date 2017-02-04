package settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import Graph.Location;
import WorldObjects.*;
import Main.*;

/**
 * @author Ashwin Gokhale.
 */
public class SettingsParser {
	public static void loadLocations(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("./src/settings/Locations.txt"));

			String line = br.readLine();    // Loc_name,Latitude,Longitude,X,Y,vehicleTypes (Aircraft, Bart, Bicycle, Bus, Car, Taxi)

			while ((line = br.readLine()) != null){
				// Split each line into its respective parameters
				String[] payload = line.split(",");

//				// Split vehicle types and parse them based on their values
				String[] types = payload[3].split("\\|");
				boolean[] vehicleTypes = new boolean[types.length];
				for (int i = 0; i < types.length; i++) {
					vehicleTypes[i] = types[i].equals("1");
				}

				// Create a new Location object and add it to the list
				Location loc = new Location(payload[0], Integer.parseInt(payload[1]), Integer.parseInt(payload[2]) , vehicleTypes);
				Visualize.locations.add(loc);
				Visualize.g.addVertex(loc);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createPaths(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("./src/settings/Paths.txt"));

			String line = br.readLine();    // startPoint,destPoint

			while ((line = br.readLine()) != null){
				String[] payload = line.split(",");
				Location from = Visualize.g.getGraph().get(payload[0]);
				Location to = Visualize.g.getGraph().get(payload[1]);
				for (int i = 0; i < 6; i++) {
					if (from.getVehicleTypes()[i] && to.getVehicleTypes()[i])
						Visualize.g.addEdge(from, to, Location.getDistance(from,to) , Location.vehicleTypeList[i]);
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void loadPassengers(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("./src/settings/People.txt"));

			String line = br.readLine();    // name,currentLocation,destination,preference,vehiclePreference

			while ((line = br.readLine()) != null){
				String[] payload = line.split(",");
				Visualize.passengers.add(new Passenger(payload[0], Visualize.g.getGraph().get(payload[1]), Visualize.g.getGraph().get(payload[2]), Integer.parseInt(payload[3]), payload[4]));
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void loadVehicles(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("./src/settings/Vehicles.txt"));

			String line;

			while ((line = br.readLine()) != null){
				String[] payload = line.split(",");

				switch (payload[0]){
					case "Car":
						new Car(payload[1], payload[2]);break;

					case "Taxi":
						new Taxi(payload[1], payload[2]);break;

					case "Bus":
						new Bus(payload[1], payload[2]);break;

					case "Bart":
						new Bart(payload[1], payload[2]);break;

					case "Aircraft":
						new Aircraft(payload[1], payload[2]);break;

					case "Bicycle":
						new Bicycle(payload[1], payload[2]);break;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
