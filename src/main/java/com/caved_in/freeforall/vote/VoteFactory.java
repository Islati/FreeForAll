package com.caved_in.freeforall.vote;

import com.caved_in.commons.time.Cooldown;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.runnables.ExecuteVoteRunnable;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class VoteFactory {
	private static Vote activeVote = null;
	private static Cooldown voteCastCooldown = new Cooldown(300);
	private static final long VOTE_EXECUTE_DELAY = 600;

	public static boolean hasActiveVote() {
		return activeVote != null;
	}

	public static Vote getActiveVote() {
		return activeVote;
	}

	public static void setActiveVote(Vote vote) {
		activeVote = vote;
		activeVote.announce();
		Game.runnableManager.runTaskLater(new ExecuteVoteRunnable(), VOTE_EXECUTE_DELAY);
	}

	public static void clearActiveVote() {
		activeVote = null;
	}

	public static Vote createVote(ChatCommand chatCommand, String caster, String... args) {
		Vote vote = null;
		switch (chatCommand) {
			case VOTE_KICK_PLAYER:
				vote = new VoteKick(caster, args);
				break;
			case VOTE_MAP_CHANGE:
				vote = new VoteMap(caster, args);
				break;
			default:
				break;
		}
		return vote;
	}
}
