package com.caved_in.freeforall.perks;

import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Set;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public interface IPerk {
	public String getPerkName();

	public List<String> getPerkDescription();

	public Set<PotionEffect> getEffects();

	public int getPurchaseCost();

	public boolean isTieredPerk();

	public String getPerkRequired();
}
