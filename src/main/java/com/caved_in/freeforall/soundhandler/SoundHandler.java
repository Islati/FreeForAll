package com.caved_in.freeforall.soundhandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class SoundHandler {

	public static void playSoundForPLayer(Player Player, SoundEffect Sound) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound " + getSound(Sound) + " " + Player.getName());
	}

	public enum SoundEffect {
		TerroristsWin,
		CounterTerroristsWin,
		LockAndLoad,
		HeadShots,
		DoubleKill,
		Dominating,
		UltraKill,
		MegaKill,
		Rampage,
		Unstoppable,
		Godlike,
		HOLYSHIT

	}

	public static String getSound(SoundEffect Sound) {
		switch (Sound) {
			case CounterTerroristsWin:
				return "mob.horse.breathe1";
			case HeadShots:
				return "mob.horse.hit";
			case LockAndLoad:
				return "mob.horse.breathe3";
			case TerroristsWin:
				return "mob.horse.breathe2";
			case DoubleKill:
				return "mob.horse.leather";
			case Dominating:
				return "mob.horse.so";
			case UltraKill:
				return "mob.horse.soft";
			case MegaKill:
				return "mob.horse.soft";
			case Rampage:
				return "mob.horse.soft";
			case Unstoppable:
				return "mob.horse.soft";
			case Godlike:
				return "mob.horse.jump";
			case HOLYSHIT:
				return "mob.horse.soft";
			default:
				break;
		}
		return "";
	}
}
