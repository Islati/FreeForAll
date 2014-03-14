package com.caved_in.freeforall.vote;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public interface IVote {
	public boolean hasVoted(String playerName);

	public void addYes();

	public void addNo();

	public void addVote(ChatCommand chatCommand);

	public void setVoted(String playerName);

	public void execute();

	public void announce();

	public String getCaster();

	public boolean canExecute();

	public int getYesVotes();

	public int getNoVotes();
}
