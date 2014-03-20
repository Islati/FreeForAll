package com.caved_in.freeforall.config;

import org.bukkit.Location;

import java.util.*;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class WorldSpawns {
	private String worldName;
	private List<Location> spawnLocations = new ArrayList<>();

	public WorldSpawns(String worldName) {
		this.worldName = worldName;
	}

	public WorldSpawns(String worldName, List<XmlSpawnPoint> spawnLocations) {
		this(worldName);
		initLocations(spawnLocations);
	}

	public void initLocations(List<XmlSpawnPoint> spawnPoints) {
		for(XmlSpawnPoint xmlSpawnPoint : spawnPoints) {
			add(xmlSpawnPoint);
		}
	}

	public boolean add(XmlSpawnPoint spawnLocation) {
		return spawnLocations.add(spawnLocation.getLocation());
	}

	public boolean add(Location location) {
		return spawnLocations.add(location);
	}

	public boolean remove(Location location) {
		return spawnLocations.remove(location);
	}

	public boolean remove(XmlSpawnPoint spawnLocation) {
		return spawnLocations.remove(spawnLocation.getLocation());
	}

	public Location getRandomSpawn() {
		return spawnLocations.get(new Random().nextInt(spawnLocations.size()));
	}

	public List<Location> getLocations() {
		return this.spawnLocations;
	}

	public int getSpawnCount() {
		return spawnLocations.size();
	}


	public String getWorldName() {
		return worldName;
	}
}
