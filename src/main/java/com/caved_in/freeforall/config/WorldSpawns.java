package com.caved_in.freeforall.config;

import com.caved_in.freeforall.TeamType;

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
	private boolean modified = false;
	private List<TeamSpawnLocation> spawnLocations = new ArrayList<>();
	private Map<TeamType, List<TeamSpawnLocation>> teamSpawns = new HashMap<>();

	public WorldSpawns(String worldName) {
		this.worldName = worldName;
	}

	public WorldSpawns(String worldName, List<TeamSpawnLocation> spawnLocations) {
		this.worldName = worldName;
		this.spawnLocations = spawnLocations;
	}

	public void add(TeamSpawnLocation spawnLocation) {
		spawnLocations.add(spawnLocation);
		modified = true;
	}

	public void remove(TeamSpawnLocation spawnLocation) {
		spawnLocations.remove(spawnLocation);
		modified = true;
	}

	/**
	 * Filter a list of spawn locations based on the team given
	 *
	 * @param teamType Team we wish to get the spawn locations of
	 * @return Set of the spawnLocations for the given team
	 */
	public List<TeamSpawnLocation> getSpawnLocations(TeamType teamType) {
		if (modified) {
			teamSpawns.clear();
			teamSpawns.put(TeamType.TERRORIST, new ArrayList<TeamSpawnLocation>());
			teamSpawns.put(TeamType.COUNTER_TERRORIST, new ArrayList<TeamSpawnLocation>());
			for (TeamSpawnLocation spawnLocation : spawnLocations) {
				teamSpawns.get(spawnLocation.getTeamType()).add(spawnLocation);
			}
			modified = false;
		}
		return teamSpawns.get(teamType);
	}

	public TeamSpawnLocation getRandomSpawn(TeamType teamType) {
		List<TeamSpawnLocation> spawns = getSpawnLocations(teamType);
		return spawns.get(new Random().nextInt(spawns.size()));
	}

	public String getWorldName() {
		return worldName;
	}
}
