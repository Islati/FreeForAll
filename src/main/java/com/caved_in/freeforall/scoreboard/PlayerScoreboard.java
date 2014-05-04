package com.caved_in.freeforall.scoreboard;

import com.caved_in.commons.Commons;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PlayerScoreboard {
	private static String dummyObjectiveName = ChatColor.RESET.toString();
	private Objective objective;
	private Scoreboard scoreboard;

	private static String leadingScore = ChatColor.GOLD + "Top Score:";
	private static String killsScore = ChatColor.AQUA + "Kills:";
	private static String deathsScore = ChatColor.RED + "Deaths:";
	private static String killsStreak = ChatColor.YELLOW + "Kill Streak:";
	private static String XP = ChatColor.GREEN + "XP:";


	public PlayerScoreboard() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective(dummyObjectiveName, "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Free For All");
	}

	public void clearScoreboard() {
		try {
			scoreboard.resetScores(getName(leadingScore));
			scoreboard.resetScores(getName(killsScore));
			scoreboard.resetScores(getName(deathsScore));
			scoreboard.resetScores(getName(killsStreak));
			scoreboard.resetScores(getName(XP));
		} catch (Exception ex) {
			Commons.messageConsole("Some scores were null");
		}
		//this.Board.clearSlot(DisplaySlot.SIDEBAR);
	}

	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}

	public void updateScoreboardData(GamePlayer player) {
		//The score for the highest scoring player
		Score highestScore = objective.getScore(getName(leadingScore));
		//The amount of kills the player has
		Score playerKillScore = objective.getScore(getName(killsScore));
		//Get the amount of times the player's died
		Score playerDeathScore = objective.getScore(getName(deathsScore));
		//Get the players current killstreak
		Score playerKillStreak = objective.getScore(getName(killsStreak));
		//Get the players current amount of XP for the scoreboard.
		Score playerXPScore = objective.getScore(getName(XP));
		//Set the highest score
		highestScore.setScore(FakeboardHandler.getHighestScore());
		playerKillScore.setScore(player.getPlayerScore());
		playerDeathScore.setScore(player.getPlayerDeaths());
		playerKillStreak.setScore(player.getKillStreak());
        UUID uuid = Bukkit.getPlayer(player.getName()).getUniqueId();
		playerXPScore.setScore((int) Players.getData(uuid).getCurrency());
	}

	public enum ScoreType {
		Terrorist, CounterTerrorist, Kills, Deaths, Killstreak
	}

	public OfflinePlayer getName(String Name) {
		return Bukkit.getOfflinePlayer(Name);
	}
}
