package com.caved_in.freeforall.config;

import com.caved_in.freeforall.guns.GunType;
import com.caved_in.freeforall.guns.GunWrapper;
import org.simpleframework.xml.Element;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class XmlGun {

	@Element(name = "gun_name")
	private String gunName = "AK-47";

	@Element(name = "gun_type")
	private String gunCategory = "Assault";

	@Element(name = "gun_price")
	private int gunPrice = 0;

	@Element(name = "default_gun")
	private boolean defaultGun = true;

	private GunType gunType = GunType.ASSAULT;

	private GunWrapper gunWrapper;

	public XmlGun(@Element(name = "gun_name") String gunName,
				  @Element(name = "gun_type") String gunCategory,
				  @Element(name = "gun_price") int gunPrice,
				  @Element(name = "default_gun") boolean defaultGun) {
		this.gunName = gunName;
		this.gunCategory = gunCategory;
		this.gunPrice = gunPrice;
		this.defaultGun = defaultGun;
		this.gunType = GunType.valueOf(gunCategory.toUpperCase());
		initGunWrapper();
	}

	public XmlGun() {
		initGunWrapper();
	}

	private void initGunWrapper() {
		this.gunWrapper = new GunWrapper(gunName, gunPrice, defaultGun, gunType);
	}

	public boolean isDefaultGun() {
		return defaultGun;
	}

	public int getGunPrice() {
		return gunPrice;
	}

	public String getGunCategory() {
		return gunCategory;
	}

	public String getGunName() {
		return gunName;
	}

	public GunType getGunType() {
		return gunType;
	}

	public GunWrapper getGunWrapper() {
		return gunWrapper;
	}
}
