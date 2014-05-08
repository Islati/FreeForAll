package com.caved_in.freeforall.menus.loadoutselector.weaponselection.secondary;

import com.caved_in.commons.item.Items;
import com.caved_in.commons.player.Players;
import com.caved_in.commons.player.PlayerWrapper;
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
public class SecondarySelectionItem extends MenuItem {
	private String gunID = "";
	private int loadoutNumber = 1;
	private GunWrapper gunData;
	private boolean hasAlreadyClicked = false;


	public SecondarySelectionItem(GunWrapper gunWrapper, ItemStack gunItemStack, int loadoutNumber, boolean purchased) {
		super(Items.getName(gunItemStack), new MaterialData(gunItemStack.getType()));
		this.gunData = gunWrapper;
		this.gunID = gunWrapper.getGunName();
		List<String> itemDescription = Items.getLore(gunItemStack);
		itemDescription.add("");
		itemDescription.add(purchased ? ChatColor.GREEN + "You've unlocked this Item!" : ChatColor.AQUA + "Costs " + gunData.getGunPrice() + " XP to unlock");
		this.setDescriptions(itemDescription);
		this.loadoutNumber = loadoutNumber;
	}

	@Override
	public void onClick(Player player) {
		GamePlayer GamePlayer = FakeboardHandler.getPlayer(player);
		if (this.gunData.isDefaultGun() || GamePlayer.hasGun(this.gunID) || this.gunData.getGunPrice() == 0) {
			GamePlayer.getLoadout(this.loadoutNumber).setSecondary(this.gunID);
			player.sendMessage(ChatColor.GREEN + "The '" + this.getText() + "' is now your secondary for loadout #" + this.loadoutNumber);
			this.getMenu().switchMenu(player, new LoadoutCreationMenu().getMenu(player));
		} else {
			if (!this.hasAlreadyClicked) {
				this.hasAlreadyClicked = true;
				player.sendMessage(ChatColor.YELLOW + "Click again to purchase the " + this.getText());
			} else {
				PlayerWrapper playerWrapper = Players.getData(player.getUniqueId());
				double playerBalance = playerWrapper.getCurrency();
				if (playerBalance >= this.gunData.getGunPrice()) {
					this.hasAlreadyClicked = false;
					playerWrapper.removeCurrency(this.gunData.getGunPrice());
					Players.updateData(playerWrapper);
					GamePlayer.unlockGun(this.gunID);
					player.sendMessage(ChatColor.AQUA + "You've unlocked the " + this.getText() + ChatColor.AQUA + "! You have " + ChatColor.GREEN + ((int)
							playerWrapper.getCurrency()) + " XP " + ChatColor.AQUA + "remaining!");
					//Update the players gun
					GamePlayer.getLoadout(this.loadoutNumber).setSecondary(this.gunID);
					player.sendMessage(ChatColor.GREEN + "The '" + this.getText() + "' is now your secondary for loadout #" + this.loadoutNumber);
					this.getMenu().switchMenu(player, new LoadoutCreationMenu().getMenu(player));
				} else {
					player.sendMessage(ChatColor.RED + "You don't have enough XP to unlock this.");
					this.hasAlreadyClicked = false;
				}
			}
		}
	}

}
