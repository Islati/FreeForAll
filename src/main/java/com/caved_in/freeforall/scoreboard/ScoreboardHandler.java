package com.caved_in.freeforall.scoreboard;

import com.caved_in.freeforall.TeamType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class ScoreboardHandler {
	private Scoreboard scoreboard;
	private Objective objective;

	public ScoreboardHandler() {
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		scoreboard = scoreboardManager.getNewScoreboard();
		objective = scoreboard.registerNewObjective("teamkilz", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Team Scores");
	}

	public void clearScores() {
		scoreboard.resetScores(Bukkit.getOfflinePlayer(ChatColor.GOLD + "CTerrorist:"));
		scoreboard.resetScores(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Terrorist:"));
	}

	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}

	public void setScore(TeamType team, int teamScore) {
		switch (team) {
			case COUNTER_TERRORIST:
				Score counterTerroristScore = this.objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "CTerrorist:"));
				counterTerroristScore.setScore(teamScore);
				break;
			case TERRORIST:
				Score terroristScore = this.objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Terrorist:"));
				terroristScore.setScore(teamScore);
				break;
			default:
				break;
		}
	}
}
