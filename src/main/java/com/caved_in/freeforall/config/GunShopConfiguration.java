package com.caved_in.freeforall.config;

import com.caved_in.freeforall.guns.GunWrapper;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GunShopConfiguration {
	@ElementList(name = "guns_data", type = XmlGun.class)
	private List<XmlGun> gunData = new ArrayList<>();

	private List<GunWrapper> gunWrappers = new ArrayList<>();

	public GunShopConfiguration(@ElementList(name = "guns_data", type = XmlGun.class) List<XmlGun> gunData) {
		this.gunData = gunData;
		initData();
	}

	public GunShopConfiguration() {
		gunData.add(new XmlGun());
		initData();
	}

	private void initData() {
		for (XmlGun gun : gunData) {
			gunWrappers.add(gun.getGunWrapper());
		}
	}

	public List<GunWrapper> getGunData() {
		return gunWrappers;
	}
}
