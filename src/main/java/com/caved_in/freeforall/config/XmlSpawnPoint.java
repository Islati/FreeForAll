package com.caved_in.freeforall.config;

import com.caved_in.commons.location.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
@Root(name = "Spawn_Location")
public class XmlSpawnPoint {
	@Attribute(name = "worldName")
	private String worldName = "world";

	@Element(name = "locX")
	private int locX = 0;

	@Element(name = "locY")
	private int locY = 0;

	@Element(name = "locZ")
	private int locZ = 0;

	private Location spawnLocation;

	public XmlSpawnPoint() { }

	public XmlSpawnPoint(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
		int[] xyz = Locations.getXYZ(spawnLocation);
		this.locX = xyz[0];
		this.locY = xyz[1];
		this.locZ = xyz[2];
		this.worldName = spawnLocation.getWorld().getName();
	}

	public XmlSpawnPoint(@Attribute(name = "worldName") String worldName, @Element(name = "locX") int locX, @Element(name = "locY") int locY, @Element(name = "locZ") int locZ) {
		this.worldName = worldName;
		this.locX = locX;
		this.locY = locY;
		this.locZ = locZ;
		this.spawnLocation = new Location(Bukkit.getWorld(worldName), locX, locY, locZ);
	}


	public Location getLocation() {
		return spawnLocation;
	}

	public String getWorldName() {
		return worldName;
	}
}
