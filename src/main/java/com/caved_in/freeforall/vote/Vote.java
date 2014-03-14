package com.caved_in.freeforall.vote;

import java.util.HashSet;
import java.util.Set;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public abstract class Vote implements IVote {

	private String voteCaster;
	private String[] args;

	private int yesVotes = 0;
	private int noVotes = 0;

	private Set<String> alreadyVoted = new HashSet<>();

	public Vote(String voteCaster, String[] args) {
		this.voteCaster = voteCaster;
		this.args = args;
	}

	@Override
	public boolean hasVoted(String playerName) {
		return alreadyVoted.contains(playerName);
	}

	@Override
	public void addYes() {
		yesVotes += 1;
	}

	@Override
	public void addNo() {
		noVotes += 1;
	}

	@Override
	public void addVote(ChatCommand chatCommand) {
		switch (chatCommand) {
			case YES:
				addYes();
				break;
			case NO:
				addNo();
				break;
			default:
				break;
		}
	}

	@Override
	public int getYesVotes() {
		return yesVotes;
	}

	@Override
	public int getNoVotes() {
		return noVotes;
	}

	@Override
	public void setVoted(String playerName) {
		alreadyVoted.add(playerName);
//		VoteFactory.setVoted(playerName); TODO remove the vote cooldown
	}

	@Override
	public String getCaster() {
		return voteCaster;
	}

	@Override
	public boolean canExecute() {
		return yesVotes != noVotes && (yesVotes > noVotes);
	}

	public abstract void execute();

	public abstract void announce();
}
