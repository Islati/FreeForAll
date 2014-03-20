package com.caved_in.freeforall.runnables;

import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
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
		if (Players.getOnlineCount() <= 0) {
			//No players online? Stop the game!
			GameSetupHandler.setGameInProgress(false);
			gameCurrentTicks = 0;
			//Schedule a map rotation
			Game.runnableManager.runTaskLater(new Runnable() {
				@Override
				public void run() {
					Game.rotateMap();
				}
			}, 100L);
			Game.runnableManager.cancelTask("GameEndCheck");
			return;
		}
		GamePlayer leadingPlayer = FakeboardHandler.getTopPlayer();
		int leadingSore = leadingPlayer.getPlayerScore();
		//Check if the game has been running for less than 10 minutes
		boolean gameTimeExpired = gameCurrentTicks >= gameStopTicks;
		if (leadingSore >= 75 || gameTimeExpired) {
			GameSetupHandler.setGameInProgress(false);
			gameCurrentTicks = 0;
			Players.messageAll(String.format(gameTimeExpired ? "&6Time's up, good game everyone! &a%s&6 took first place." : "&a%s&6 reached the max-score", leadingPlayer.getName()));
			GameSetupHandler.awardEndgamePoints(FakeboardHandler.getTopPlayer().getName(), 50, 25);
			Game.runnableManager.runTaskLater(new Runnable() {
				@Override
				public void run() {
					Game.rotateMap();
				}
			}, 100L);
			Game.runnableManager.cancelTask("GameEndCheck");
		}
		gameCurrentTicks += 40;
	}

}
