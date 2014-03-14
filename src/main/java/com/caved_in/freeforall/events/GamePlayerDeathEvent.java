package com.caved_in.freeforall.events;

import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GamePlayerDeathEvent extends PlayerDeathEvent {
	private static final HandlerList handlers = new HandlerList();
	private GamePlayer gamePlayer;
	private boolean keepInventory = true;

	public GamePlayerDeathEvent(PlayerDeathEvent event) {
		super(event.getEntity(), event.getDrops(), event.getDroppedExp(), event.getDeathMessage());
		this.gamePlayer = FakeboardHandler.getPlayer(event.getEntity());
	}

	public GamePlayerDeathEvent(Player player, List<ItemStack> drops, int droppedExp, String deathMessage) {
		super(player, drops, droppedExp, deathMessage);
		this.gamePlayer = FakeboardHandler.getPlayer(player);
	}

	public GamePlayerDeathEvent(Player player, List<ItemStack> drops, int droppedExp, int newExp, String deathMessage) {
		super(player, drops, droppedExp, newExp, deathMessage);
		this.gamePlayer = FakeboardHandler.getPlayer(player);
	}

	public GamePlayerDeathEvent(Player player, List<ItemStack> drops, int droppedExp, int newExp, int newTotalExp, int newLevel, String deathMessage) {
		super(player, drops, droppedExp, newExp, newTotalExp, newLevel, deathMessage);
		this.gamePlayer = FakeboardHandler.getPlayer(player);
	}

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}


	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isInventoryKept() {
		return keepInventory;
	}

	public void setKeepInventory(boolean keepInventory) {
		this.keepInventory = keepInventory;
	}
}
