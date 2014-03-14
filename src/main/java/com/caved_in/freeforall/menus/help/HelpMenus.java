package com.caved_in.freeforall.menus.help;

import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.freeforall.Game;
import org.bukkit.ChatColor;

import java.util.Map;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class HelpMenus {

	enum ItemFormat {
		DOUBLE_DASH("<name> -- <desc>"),
		SINGLE_DASH("<name> - <desc>");

		private String formatting;

		private ItemFormat(String formatting) {
			this.formatting = formatting;
		}

		@Override
		public String toString() {
			return this.formatting;
		}
	}

	enum PageDisplay {
		DEFAULT("<name> (Page <page> of <maxpage>"),
		SHORTHAND("<name> (P.<page>/<maxpage>)");

		private String formatting;

		private PageDisplay(String formatting) {
			this.formatting = formatting;
		}

		@Override
		public String toString() {
			return this.formatting;
		}
	}

	private static HelpScreen generateHelpScreen(String menuName, PageDisplay pageDisplay, ItemFormat itemFormat, ChatColor flipColorEven, ChatColor flipColorOdd) {
		HelpScreen helpScreen = new HelpScreen(menuName);
		helpScreen.setHeader(pageDisplay.toString());
		helpScreen.setFormat(itemFormat.toString());
		helpScreen.setFlipColor(flipColorEven, flipColorOdd);
		return helpScreen;
	}

	private static HelpScreen generateHelpScreen(String menuName, PageDisplay pageDisplay, ItemFormat itemFormat, ChatColor flipColorEven, ChatColor flipColorOdd, Map<String, String> helpItems) {
		HelpScreen helpScreen = new HelpScreen(menuName);
		helpScreen.setHeader(pageDisplay.toString());
		helpScreen.setFormat(itemFormat.toString());
		helpScreen.setFlipColor(flipColorEven, flipColorOdd);
		for (Map.Entry<String, String> menuItem : helpItems.entrySet()) {
			helpScreen.setEntry(menuItem.getKey(), menuItem.getValue());
		}
		return helpScreen;
	}

	public static HelpScreen getAdminCommandsHelp() {
		HelpScreen helpScreen = generateHelpScreen("Available Maps", PageDisplay.DEFAULT, ItemFormat.SINGLE_DASH, ChatColor.GREEN, ChatColor.DARK_GREEN);
		for (String worldName : Game.worldList.getContentsAsList()) {
			helpScreen.setEntry(worldName, "/forcemap " + worldName);
		}
		return helpScreen;
	}

}
