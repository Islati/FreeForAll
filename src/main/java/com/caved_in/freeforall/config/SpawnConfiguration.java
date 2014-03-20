package com.caved_in.freeforall.config;

import com.caved_in.commons.location.Locations;
import org.bukkit.Location;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class SpawnConfiguration {

	@ElementList(name = "spawnpoints", type = XmlSpawnPoint.class)
	private List<XmlSpawnPoint> spawnPoints = new ArrayList<>();

	private Map<String, WorldSpawns> worldSpawnLocations = new HashMap<>();

	public SpawnConfiguration(@ElementList(name = "spawnpoints", type = XmlSpawnPoint.class) List<XmlSpawnPoint> spawnPoints) {
		this.spawnPoints = spawnPoints;
		initializeTeamSpawns();
	}

	public SpawnConfiguration() {
		initializeTeamSpawns();
		spawnPoints.add(new XmlSpawnPoint());
	}

	private void initializeTeamSpawns() {
		//Loop through our loaded XMLSpawnPoints
		for (XmlSpawnPoint xmlSpawnPoint : spawnPoints) {
			//Get the world name of the selected spawn point
			String worldName = xmlSpawnPoint.getWorldName();
			//Check if our worldSpawns lists has an entry for this world already
			if (worldSpawnLocations.containsKey(worldName)) {
				//Add a teamspawnlocation to the instance for this world
				worldSpawnLocations.get(worldName).add(xmlSpawnPoint);
			} else {
				//Create our worldSpawns object for the given world
				WorldSpawns worldSpawns = new WorldSpawns(worldName);
				//Add a new TeamSpawnObject based on the current xmlspawnpoint
				worldSpawns.add(xmlSpawnPoint);
				//Add our worldSpawns object to the map of elements
				worldSpawnLocations.put(worldName, worldSpawns);
			}
		}
	}

	public WorldSpawns getWorldSpawns(String worldName) {
		if (!worldSpawnLocations.containsKey(worldName)) {
			worldSpawnLocations.put(worldName, new WorldSpawns(worldName));
		}
		return worldSpawnLocations.get(worldName);
	}


	public void addSpawn(Location location) {
		String worldName = Locations.getWorldName(location);
		getWorldSpawns(worldName).add(location);
		spawnPoints.add(new XmlSpawnPoint(location));
	}

}
