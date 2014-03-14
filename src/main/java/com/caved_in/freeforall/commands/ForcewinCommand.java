package com.caved_in.freeforall.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.TeamType;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import org.bukkit.command.CommandSender;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class ForcewinCommand {

	@CommandController.CommandHandler(name = "forcewin",
			description = "Forcewin the game to rotate players",
			permission = "gungame.admin",
			usage = "/forcewin [Team]"
	)
	public void onForceWinCommand(CommandSender sender, String[] args) {
		//Check for command arguments
		if (args.length > 0) {
			//Get the argument passed; Our team argument
			String winningTeam = args[0];
			//Switch on the argument passed
			switch (winningTeam.toLowerCase()) {
				case "ct":
				case "t":
					//The winning team is either terrorist or counterterrorist; Add 50 to their score
					FakeboardHandler.getTeam(TeamType.getTeamByInitials(winningTeam)).addTeamScore(50);
					break;
				default:
					//They didn't enter a valid team name, so send a list of available ones
					PlayerHandler.sendMessage(sender, "&cThe available teams are &eT&c and &eCT");
			}
		} else {
			//Send them the invalid command message
			PlayerHandler.sendMessage(sender, Messages.INVALID_COMMAND_USAGE("team (&7CT&8/&7T&e)"));
		}
	}
}
