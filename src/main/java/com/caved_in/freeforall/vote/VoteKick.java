package com.caved_in.freeforall.vote;

import com.caved_in.commons.Commons;
import com.caved_in.commons.Messages;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.commons.utilities.StringUtil;
import com.caved_in.freeforall.GameMessages;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class VoteKick extends Vote {

	private String playerToKick;
	private String kickReason;

	public VoteKick(String voteCaster, String... args) {
		super(voteCaster, args);
		addYes();
		setVoted(voteCaster);
		playerToKick = args[0];
		kickReason = StringUtil.joinString(args, " ", 1);
	}

	public String getKickReason() {
		return this.kickReason;
	}

	public String getPlayerToKick() {
		return this.playerToKick;
	}

	@Override
	public void execute() {
		Commons.threadManager.runTaskOneTickLater(new Runnable() {
			@Override
			public void run() {
				PlayerHandler.kickPlayer(PlayerHandler.getPlayer(playerToKick), kickReason);
				PlayerHandler.sendMessageToAllPlayers(Messages.PLAYER_KICKED(playerToKick, kickReason));
			}
		});
	}

	@Override
	public void announce() {
		String voteKick = GameMessages.ANNOUNCE_VOTE_PLAYER_KICK(getCaster(), playerToKick, kickReason);
		PlayerHandler.sendMessageToAllPlayers(voteKick);
	}
}
