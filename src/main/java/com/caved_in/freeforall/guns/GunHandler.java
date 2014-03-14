package com.caved_in.freeforall.guns;

import com.caved_in.freeforall.Game;

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
public class GunHandler {
	private List<GunWrapper> gunPistols = new ArrayList<>();

	private List<GunWrapper> gunAssaultRifles = new ArrayList<>();

	private List<GunWrapper> gunSniperRifles = new ArrayList<>();

	private List<GunWrapper> gunSpecial = new ArrayList<>();

	private List<GunWrapper> gunShotgun = new ArrayList<>();

	private Map<String, GunWrapper> gunDefault = new HashMap<>();

	public GunHandler() {
		initData();
	}

	public void initData() {
		List<GunWrapper> gunData = Game.configuration.getGunShopConfiguration().getGunData();
		for (GunWrapper gunWrapper : gunData) {
			//Check if it's a default gun, and if so add it to the list of defaults
			if (gunWrapper.isDefaultGun()) {
				gunDefault.put(gunWrapper.getGunName(), gunWrapper);
			}

			//Sort all the gun data
			switch (gunWrapper.getGunType()) {
				case PISTOL:
					gunPistols.add(gunWrapper);
					break;
				case ASSAULT:
					gunAssaultRifles.add(gunWrapper);
					break;
				case SPECIAL:
					gunSpecial.add(gunWrapper);
					break;
				case SHOTGUN:
					gunShotgun.add(gunWrapper);
					break;
				case SNIPER:
					gunSniperRifles.add(gunWrapper);
					break;
				default:
					break;
			}
		}
	}

	public Map<String, GunWrapper> getDefaultGunMap() {
		return gunDefault;
	}

	public List<GunWrapper> getGuns(GunType gunType) {
		switch (gunType) {
			case ASSAULT:
				return gunAssaultRifles;
			case PISTOL:
				return gunPistols;
			case SHOTGUN:
				return gunShotgun;
			case SNIPER:
				return gunSniperRifles;
			case SPECIAL:
				return gunSpecial;
			default:
				return null;
		}
	}

}
