package com.caved_in.freeforall.scoreboard;

import com.caved_in.commons.Commons;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.TeamType;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

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

	private static String terroristScore = ChatColor.GOLD + "Terrorist:";
	private static String counterTerroristScore = ChatColor.GOLD + "CTerrorist:";
	private static String killsScore = ChatColor.AQUA + "Kills:";
	private static String deathsScore = ChatColor.RED + "Deaths:";
	private static String killsStreak = ChatColor.YELLOW + "Kill Streak:";
	private static String XP = ChatColor.GREEN + "XP:";


	public PlayerScoreboard() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective(dummyObjectiveName, "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Lock & Load");
	}

	public void clearScoreboard() {
		try {
			scoreboard.resetScores(getName(terroristScore));
			scoreboard.resetScores(getName(counterTerroristScore));
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
		Score terroristScore = objective.getScore(getName(PlayerScoreboard.terroristScore));
		Score counterTerroristScore = objective.getScore(getName(PlayerScoreboard.counterTerroristScore));
		Score playerKillScore = objective.getScore(getName(killsScore));
		Score playerDeathScore = objective.getScore(getName(deathsScore));
		Score playerKillStreak = objective.getScore(getName(killsStreak));
		Score playerXPScore = objective.getScore(getName(XP));
		terroristScore.setScore(FakeboardHandler.getTeam(TeamType.TERRORIST).getTeamScore());
		counterTerroristScore.setScore(FakeboardHandler.getTeam(TeamType.COUNTER_TERRORIST).getTeamScore());
		playerKillScore.setScore(player.getPlayerScore());
		playerDeathScore.setScore(player.getPlayerDeaths());
		playerKillStreak.setScore(player.getKillStreak());
		playerXPScore.setScore((int) PlayerHandler.getData(player.getName()).getCurrency());
	}

	public enum ScoreType {
		Terrorist, CounterTerrorist, Kills, Deaths, Killstreak
	}

	public void updateData(ScoreType score, GamePlayer playerWrapper) {
		switch (score) {
			case CounterTerrorist:
				Score ctScore = this.objective.getScore(getName(counterTerroristScore));
				ctScore.setScore(FakeboardHandler.getTeam(TeamType.COUNTER_TERRORIST).getTeamScore());
				break;
			case Deaths:
				Score dScore = objective.getScore(getName(deathsScore));
				dScore.setScore(playerWrapper.getPlayerDeaths());
				break;
			case Kills:
				Score kScore = objective.getScore(getName(killsScore));
				kScore.setScore(playerWrapper.getPlayerScore());
				break;
			case Killstreak:
				Score ksScore = objective.getScore(getName(killsScore));
				ksScore.setScore(playerWrapper.getKillStreak());
				break;
			case Terrorist:
				Score tScore = objective.getScore(getName(terroristScore));
				tScore.setScore(FakeboardHandler.getTeam(TeamType.TERRORIST).getTeamScore());
				break;
			default:
				break;
		}
	}

	public OfflinePlayer getName(String Name) {
		return Bukkit.getOfflinePlayer(Name);
	}
}
