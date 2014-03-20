package com.caved_in.freeforall.config;

import com.caved_in.commons.potion.Potions;
import com.caved_in.commons.potion.PotionType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.simpleframework.xml.Attribute;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class XmlPotionEffect {
	@Attribute(name = "potion_type")
	private String alias = "";

	@Attribute(name = "level")
	private int level = 1;

	private PotionEffect potionEffect;
	private boolean valid = false;

	public XmlPotionEffect(@Attribute(name = "potion_type") String alias, @Attribute(name = "level") int level) {
		this.alias = alias;
		this.level = level;
		if (PotionType.isPotionType(alias)) {
			valid = true;
			this.potionEffect = Potions.getPotionEffect(PotionType.getPotionType(alias).getPotionEffectType(), level, Integer.MAX_VALUE);
		} else {
			this.potionEffect = new PotionEffect(PotionEffectType.CONFUSION, 1, 1);
		}
	}

	public boolean isValid() {
		return valid;
	}

	public PotionEffect getPotionEffect() {
		return potionEffect;
	}
}
