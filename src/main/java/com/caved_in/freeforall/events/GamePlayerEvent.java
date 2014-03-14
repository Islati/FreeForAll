package com.caved_in.freeforall.events;

import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GamePlayerEvent extends PlayerEvent {
	private static final HandlerList handlers = new HandlerList();
	private GamePlayer GamePlayer;

	public GamePlayerEvent(Player who) {
		super(who);
		//Get the GamePlayer involved
		this.GamePlayer = FakeboardHandler.getPlayer(who);
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public GamePlayer getGamePlayer() {
		return GamePlayer;
	}
}
