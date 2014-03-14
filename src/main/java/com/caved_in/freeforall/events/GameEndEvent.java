package com.caved_in.freeforall.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GameEndEvent extends Event {

	public static final HandlerList handlers = new HandlerList();

	public GameEndEvent() {
		//Not an async event
		super(false);
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
