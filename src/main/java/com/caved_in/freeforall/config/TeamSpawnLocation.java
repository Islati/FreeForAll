package com.caved_in.freeforall.config;

import com.caved_in.freeforall.TeamType;
import org.bukkit.Location;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class TeamSpawnLocation {
	private Location location;
	private TeamType teamType;

	public TeamSpawnLocation(Location location, TeamType teamType) {
		this.location = location;
		this.teamType = teamType;
	}

	public TeamSpawnLocation(XmlSpawnPoint xmlSpawnPoint) {
		this.location = xmlSpawnPoint.getLocation();
		this.teamType = xmlSpawnPoint.getTeamType();
	}

	public TeamType getTeamType() {
		return teamType;
	}

	public Location getLocation() {
		return location;
	}

}
