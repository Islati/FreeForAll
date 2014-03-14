package com.caved_in.freeforall.config;

import com.caved_in.commons.utilities.StringUtil;
import com.caved_in.freeforall.perks.Perk;
import org.bukkit.potion.PotionEffect;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.*;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
@Root(name = "Perk")
public class XmlPerk {
	@Attribute(name = "perk_name")
	private String perkName = "Nothing";

	@Element(name = "perk_cost")
	private int perkCost = 0;

	@ElementList(name = "perk_description", entry = "line", inline = true)
	private List<String> perkDescription;

	@Attribute(name = "is_tiered")
	private boolean tiered = false;

	@Attribute(name = "required_perk")
	private String requiredPerk = "";

	@ElementList(name = "potion_effects", entry = "effect", inline = true, type = XmlPotionEffect.class)
	private List<XmlPotionEffect> xmlPotionEffects;

	private Set<PotionEffect> potionEffects = new HashSet<>();

	private Perk perk;

	public XmlPerk(@Attribute(name = "perk_name") String perkName, @Element(name = "perk_cost") int perkCost,
				   @ElementList(name = "perk_description", entry = "line", inline = true) List<String> perkDescription,
				   @Attribute(name = "is_tiered") boolean tiered,
				   @Attribute(name = "required_perk") String requiredPerk,
				   @ElementList(name = "potion_effects", entry = "effect", inline = true, type = XmlPotionEffect.class) List<XmlPotionEffect> xmlPotionEffects) {
		this.perkName = perkName;
		this.perkCost = perkCost;
		this.perkDescription = perkDescription;

		this.tiered = tiered;
		this.requiredPerk = requiredPerk;
		this.xmlPotionEffects = xmlPotionEffects;
		init();
	}

	public XmlPerk() {
		perkDescription = Arrays.asList("&eIt's actually nothing.", "&lHonestly... Nothing", "&oI promise!");
		xmlPotionEffects = Arrays.asList(new XmlPotionEffect("speed", 1));
		init();
	}

	private void init() {
		//Parse all the potion effects and instance an array
		for (XmlPotionEffect xmlPotionEffect : xmlPotionEffects) {
			potionEffects.add(xmlPotionEffect.getPotionEffect());
		}
		List<String> description = new ArrayList<String>();
		if (perkDescription.size() > 0) {
			for(String line : perkDescription) {
				description.add(StringUtil.formatColorCodes(line));
			}
		}
		//Generate the perk
		if (!isTiered()) {
			this.perk = new Perk(perkName, perkCost, description, potionEffects);
		} else {
			this.perk = new Perk(perkName, perkCost, description, potionEffects, tiered, requiredPerk);
		}
	}

	public String getRequiredPerk() {
		return requiredPerk;
	}

	public boolean isTiered() {
		return tiered;
	}

	public List<String> getPerkDescription() {
		return perkDescription;
	}

	public int getPerkCost() {
		return perkCost;
	}

	public String getPerkName() {
		return perkName;
	}

	public Set<PotionEffect> getPotionEffects() {
		return potionEffects;
	}

	public Perk getPerk() {
		return perk;
	}
}
