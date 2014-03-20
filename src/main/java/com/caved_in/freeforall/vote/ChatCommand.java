package com.caved_in.freeforall.vote;

import com.caved_in.commons.Messages;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.GameMessages;
import org.bukkit.entity.Player;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public enum ChatCommand {
	VOTE_KICK_PLAYER("kick", 2) {
		@Override
		public void doCommand(Player player, String... args) {
			if (!VoteFactory.hasActiveVote()) {
				String playerKick = args[0];
				if (Players.isOnline(playerKick)) {
					VoteFactory.setActiveVote(VoteFactory.createVote(VOTE_KICK_PLAYER, player.getName(), args));
				} else {
					Players.sendMessage(player, Messages.PLAYER_OFFLINE(playerKick));
				}
			} else {
				Players.sendMessage(player, GameMessages.VOTE_ALREADY_IN_PROGRESS);
			}
		}
	},
	VOTE_MAP_CHANGE("map", 1) {
		@Override
		public void doCommand(Player player, String... args) {
			if (!VoteFactory.hasActiveVote()) {
				if (args.length > 0) {
					String mapName = args[0];
					if (Game.isValidMap(mapName)) {
						VoteFactory.setActiveVote(VoteFactory.createVote(VOTE_MAP_CHANGE, player.getName(), args));
					} else {
						Players.sendMessage(player, GameMessages.INVALID_MAP_NAME(mapName));
					}
				}
			} else {
				Players.sendMessage(player, GameMessages.VOTE_ALREADY_IN_PROGRESS);
			}
		}
	},
	YES("yes", 0) {
		@Override
		public void doCommand(Player player, String... args) {
			if (VoteFactory.hasActiveVote()) {
				Vote vote = VoteFactory.getActiveVote();
				String playerName = player.getName();
				if (!vote.hasVoted(playerName)) {
					vote.addYes();
					Players.sendMessage(player, "&aThanks for voting!");
				} else {
					Players.sendMessage(player, GameMessages.VOTE_ALREADY_CASTED);
				}
			} else {
				Players.sendMessage(player, GameMessages.NO_ACTIVE_VOTE);
			}
		}
	},
	NO("no", 0) {
		@Override
		public void doCommand(Player player, String... args) {
			if (VoteFactory.hasActiveVote()) {
				Vote vote = VoteFactory.getActiveVote();
				String playerName = player.getName();
				if (!vote.hasVoted(playerName)) {
					vote.addNo();
					Players.sendMessage(player, "&aThanks for voting!");
				} else {
					Players.sendMessage(player, GameMessages.VOTE_ALREADY_CASTED);
				}
			} else {
				Players.sendMessage(player, GameMessages.NO_ACTIVE_VOTE);
			}
		}
	};

	/* This is the method that each command will call */
	public abstract void doCommand(Player player, String... args);

	private static final Map<String, ChatCommand> chatCommands = new HashMap<>();

	static {
		//Instance all the valid commands
		for (ChatCommand chatCommand : EnumSet.allOf(ChatCommand.class)) {
			chatCommands.put(chatCommand.command, chatCommand);
		}
	}

	private final String command;
	private final int minArgs;

	ChatCommand(String command, int minArgs) {
		this.command = command;
		this.minArgs = minArgs;
	}

	public int getMinArgs() {
		return minArgs;
	}

	public static boolean isValidCommand(String command) {
		return chatCommands.containsKey(command.toLowerCase());
	}

	public static ChatCommand getCommand(String command) {
		return chatCommands.get(command.toLowerCase());
	}
}
