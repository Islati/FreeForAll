package com.caved_in.freeforall.runnables;

import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.TeamType;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GameOverRunnable implements Runnable {
	private static final int gameStopTicks = 12000;
	int gameCurrentTicks = 0;

	@Override
	public void run() {
		//Check if we've got no players online
		if (PlayerHandler.getOnlinePlayersCount() <= 0) {
			//No players online? Stop the game!
			GameSetupHandler.setGameInProgress(false);
			gameCurrentTicks = 0;
			//Schedule a map rotation
			Game.runnableManager.runTaskLater(new Runnable() {
				@Override
				public void run() {
					Game.rotateMap(true);
				}
			}, 100L);
			Game.runnableManager.cancelTask("GameEndCheck");
			return;
		}
		//Get the scores for both teams; Terrorist and CounterTerrorist
		int terroristScore = FakeboardHandler.getTeam(TeamType.TERRORIST).getTeamScore();
		int counterTerroristScore = FakeboardHandler.getTeam(TeamType.COUNTER_TERRORIST).getTeamScore();

		//Check if the game has been running for less than 10 minutes
		boolean gameTimeExpired = gameCurrentTicks >= gameStopTicks;
		if ((terroristScore >= 50 || counterTerroristScore >= 50) || gameTimeExpired) {
			GameSetupHandler.setGameInProgress(false);
			gameCurrentTicks = 0;
			PlayerHandler.sendMessageToAllPlayers(String.format(gameTimeExpired ? "TIMES UP; &6%s WIN!" : "&6%s WIN!", terroristScore >= 50 ? "TERRORISTS" : "COUNTER TERRORISTS"));
			GameSetupHandler.awardEndgamePoints(terroristScore >= 50 ? TeamType.TERRORIST : TeamType.COUNTER_TERRORIST, 75, 50);
			Game.runnableManager.runTaskLater(new Runnable() {
				@Override
				public void run() {
					Game.rotateMap(true);
				}
			}, 100L);
			Game.runnableManager.cancelTask("GameEndCheck");
		}
		gameCurrentTicks += 40;
	}

}
