package com.caved_in.freeforall.gamehandler;

import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.soundhandler.SoundHandler;
import com.caved_in.freeforall.soundhandler.SoundHandler.SoundEffect;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class KillstreakHandler {
	public static void HandleKillStreak(GamePlayer gamePlayer) {
		Player player = gamePlayer.getPlayer();
		int playerKills = gamePlayer.getKillStreak();
		if (playerKills > 0 && playerKills <= 9) {
			switch (playerKills) {
				case 2:
					SoundHandler.playSoundForPLayer(player, SoundEffect.DoubleKill);
					break;
				case 3:
					SoundHandler.playSoundForPLayer(player, SoundEffect.Dominating);
					Game.crackShotAPI.giveWeapon(player, "Grenade", 1);
					break;
				case 4:
					SoundHandler.playSoundForPLayer(player, SoundEffect.UltraKill);
					break;
				case 5:
					SoundHandler.playSoundForPLayer(player, SoundEffect.MegaKill);
					Game.crackShotAPI.giveWeapon(player, "CocoPops", 1);
					break;
				case 6:
					SoundHandler.playSoundForPLayer(player, SoundEffect.Rampage);
					break;
				case 7:
					SoundHandler.playSoundForPLayer(player, SoundEffect.Unstoppable);
					Game.crackShotAPI.giveWeapon(player, "Airstrike", 1);
					break;
				case 8:
					SoundHandler.playSoundForPLayer(player, SoundEffect.Godlike);
					break;
				default:
					break;
			}
		} else if (playerKills > 9) {
			SoundHandler.playSoundForPLayer(player, SoundEffect.HOLYSHIT);
		}
		gamePlayer.getPlayer().updateInventory();
	}
}
