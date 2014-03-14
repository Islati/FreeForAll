package com.caved_in.freeforall.menus.closebehaviours;

import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import me.xhawk87.PopupMenuAPI.MenuCloseBehaviour;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class LoadoutMenuCloseBehaviour implements MenuCloseBehaviour {

	private static LoadoutMenuCloseBehaviour instance = null;

	protected LoadoutMenuCloseBehaviour() {

	}

	public static LoadoutMenuCloseBehaviour getInstance() {
		//If there's no instance of this behaviour, create one
		if (instance == null) {
			//Synchronize it to make it thread safe. Syncing inside the
			//Method as opposed to the entire method itself for performance increases
			//And lazy instancing.
			synchronized (LoadoutMenuCloseBehaviour.class) {
				//Double check the instance is null for sync assurance
				if (instance == null) {
					instance = new LoadoutMenuCloseBehaviour();
				}
			}
		}
		return instance;

	}

	@Override
	public void onClose(Player player) {
		//When the player closes the menu, remove their 'AFK' status
		FakeboardHandler.getPlayer(player).setAfk(false, false);
	}
}
