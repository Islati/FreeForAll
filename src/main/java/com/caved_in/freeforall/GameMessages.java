package com.caved_in.freeforall;

import com.caved_in.freeforall.vote.Vote;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GameMessages {
	private static final String PREFIX = "[Tunnels] ";
	public static final String LOADOUT_EDIT_INSTRUCTION = "&aTo edit your loadouts, use &e/loadout";
	public static final String PLAYER_DATA_LOAD_ERROR = "&ePlease Relog; There was an error loading your data &r:&b'&r(";
	public static final String GAME_MUST_BEGIN_LOADOUT_SELECTION = "&eThe round must begin before you can select a class; You can edit your classes however, by using &a/loadout";
	public static final String AFK_COMMAND_ON_COOLDOWN = "&7You can only use this command once every &o10&r&7 seconds; please wait and try again soon.";
	public static final String VOTE_ALREADY_CASTED = "&cYou've already casted your vote";
	public static final String VOTE_ALREADY_IN_PROGRESS = "&eSorry, but there's already a vote in progress";
	public static final String NO_ACTIVE_VOTE = "&eThere's nothing being voted on, sorry!";
	public static final String SELECT_PRIMARY_WEAPON_TYPE = "&aSelect your primary weapon type";
	public static final String SELECT_SECONDARY_WEAPON_TYPE = "&aSelect your secondary weapon type";
	public static final String SELECT_ACTIVE_PERK = "&aSelect your active perk";
	public static final String USE_MAPS_COMMAND = "&eUse the command &a/maps&e to see a list of available maps";

	public static String MAP_CHANGED(String to) {
		return String.format("&7The map has been changed to &l%s", to);
	}

	public static String ANNOUNCE_VOTE_MAP_CHANGE(String voteCaster, String mapName) {
		return String.format("&a%s&e has voted that the map be switched to &b%s&e. If you want to switch the map to &b%s&e, type &a!yes&e in chat, otherwise type &c!no", voteCaster, mapName, mapName);
	}

	public static String ANNOUNCE_VOTE_PLAYER_KICK(String voteCaster, String playerName, String reason) {
		return String.format("&a%s&e wants to kick &a%s&e for '&c%s&e'; To kick &a%s&e type &a!yes&e in chat, otherwise type &c!no", voteCaster, playerName, reason, playerName);
	}

	public static String VOTE_FAILED(int yesVoteCount, int noVoteCount) {
		return String.format("&eThe vote has &cfailed&e with &a%s&e yes' and &c%s&e no's", yesVoteCount, noVoteCount);
	}

	public static String VOTE_FAILED(Vote vote) {
		return VOTE_FAILED(vote.getYesVotes(), vote.getNoVotes());
	}

	public static String INSUFFICIENT_CHAT_COMMAND_ARGUMENTS(String command, int argsRequired) {
		return String.format("&eTo use &a!%s&e you need &a%s&e arguments", command, argsRequired);
	}

	public static String INVALID_CHAT_COMMAND(String command) {
		return String.format("&c%s&e is not a valid chat command, please try again.", command);
	}

	public static String INVALID_MAP_NAME(String mapName) {
		return String.format("&c%s&e is not a valid map name; To view a list of all the maps, use &a/maps", mapName);
	}
}
