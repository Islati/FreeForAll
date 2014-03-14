package com.caved_in.freeforall.menus.loadoutselector.weaponselection.primary;

import com.caved_in.commons.items.ItemHandler;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.events.CustomEventHandler;
import com.caved_in.freeforall.events.GunPurchaseEvent;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.guns.GunWrapper;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutCreationMenu;
import me.xhawk87.PopupMenuAPI.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PrimarySelectionItem extends MenuItem {
	private String gunID = "";
	private int loadoutNumber = 1;
	private GunWrapper gunData;
	private boolean hasAlreadyClicked = false;

	public PrimarySelectionItem(GunWrapper gunWrapper, ItemStack gunItemStack, int loadoutNumber, boolean purchased) {
		super(ItemHandler.getItemName(gunItemStack), new MaterialData(gunItemStack.getType()));
		this.gunData = gunWrapper;
		this.gunID = gunWrapper.getGunName();
		List<String> itemDescription = ItemHandler.getItemLore(gunItemStack);
		itemDescription.add("");
		itemDescription.add(purchased ? ChatColor.GREEN + "You've unlocked this Item!" : ChatColor.AQUA + "Costs " + gunData.getGunPrice() + " XP to unlock");
		this.setDescriptions(itemDescription);
		this.loadoutNumber = loadoutNumber;
	}

	@Override
	public void onClick(Player player) {
		GamePlayer gamePlayer = FakeboardHandler.getPlayer(player);
		//Check if its a default gun or they already purchased it
		if (gamePlayer.hasGun(gunID) || gunData.isDefaultGun() || gunData.getGunPrice() == 0) {
			gamePlayer.getLoadout(loadoutNumber).setPrimary(gunID);
			PlayerHandler.sendMessage(player, String.format("&aThe &e%s&a is now your primary weapon for loadout #&e%s", getText(), loadoutNumber));
			getMenu().switchMenu(player, new LoadoutCreationMenu().getMenu(player));
		} else {
			//Check if they've already clicked this item
			if (!hasAlreadyClicked) {
				hasAlreadyClicked = true;
				PlayerHandler.sendMessage(player, "&eClick again to purchase the " + getText());
			} else {
				hasAlreadyClicked = false;
				//Second click? Create a new gun purchase event, and call it!
				GunPurchaseEvent gunPurchaseEvent = new GunPurchaseEvent(player, gunData, loadoutNumber);
				CustomEventHandler.handleGunPurchaseEvent(gunPurchaseEvent);
			}
		}
	}
}
