package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.freeforall.Game;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class CommandRegister {
	public CommandRegister(Game Plugin) {
		CommandController.registerCommands(Plugin, new ForcemapCommand());
		CommandController.registerCommands(Plugin, new ForcewinCommand());
		CommandController.registerCommands(Plugin, new AddSpawnpointCommand());
		CommandController.registerCommands(Plugin, new SpawnsCommand());
		CommandController.registerCommands(Plugin, new AfkCommand());
		CommandController.registerCommands(Plugin, new MapsCommand());
		CommandController.registerCommands(Plugin, new GameCommand());
		CommandController.registerCommands(Plugin, new GemCommand());
	}
}
