package com.caved_in.freeforall.config;

import com.caved_in.commons.sql.DatabaseConnector;
import com.caved_in.freeforall.loadout.Loadout;
import com.caved_in.freeforall.perks.PerkHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class LoadoutSQL extends DatabaseConnector {
	private static String loadoutTable = "guns_loadouts";
	private static String playerColumn = "Player";
	private static String loadoutNumberColumn = "Loadout";
	private static String primaryWeaponColumn = "PrimaryG";
	private static String secondaryWeaponColumn = "Secondary";
	private static String perkColumn = "Perk";

	private Set<String> playersWithData = new HashSet<>();

	private static String getDataStatement = "SELECT * FROM " + loadoutTable + " WHERE " + playerColumn + "=?";
	private static String insertLoadoutStatement = "INSERT INTO " + loadoutTable + " (" + playerColumn + ", " + loadoutNumberColumn + ", " + primaryWeaponColumn + ", " + secondaryWeaponColumn + ", " + perkColumn + ") VALUES (?,?,?,?,?)";
	private static String updateLoadoutStatement = "UPDATE " + loadoutTable + " SET " + primaryWeaponColumn + "=?, " + secondaryWeaponColumn + "=?, " + perkColumn + "=? WHERE " + playerColumn + "=? AND " + loadoutNumberColumn + "=?";

	private static String creationStatement = "CREATE TABLE IF NOT EXISTS `[DB]`.`guns_loadouts` (`ID` int(10) unsigned NOT NULL AUTO_INCREMENT, `Player` text NOT NULL, `Loadout` text NOT NULL, `PrimaryG` text NOT NULL, `Secondary` text NOT NULL,`Perk` text NOT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;";

	public LoadoutSQL(SqlConfiguration sqlConfig) {
		super(
				sqlConfig.getHost(),
				sqlConfig.getPort(),
				sqlConfig.getDatabase(),
				sqlConfig.getUsername(),
				sqlConfig.getPassword()
		);
		creationStatement = creationStatement.replace("[DB]", sqlConfig.getDatabase());
	//	execute(creationStatement);
	}

	public boolean hasData(String playerName) {
		boolean hasData = playersWithData.contains(playerName);
		if (!hasData) {
			PreparedStatement preparedStatement = prepareStatement(getDataStatement);
			try {
				preparedStatement.setString(1, playerName);
				hasData = preparedStatement.executeQuery().next();
				//If the player has data, then put them into the array of players that have data
				if (hasData) {
					playersWithData.add(playerName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(preparedStatement);
			}
		}
		return hasData;
	}

	public Map<Integer, Loadout> getLoadouts(String playerName) {
		Map<Integer, Loadout> playerLoadouts = new HashMap<>();
		PreparedStatement preparedStatement = prepareStatement(getDataStatement);
		try {
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int loadoutNumber = resultSet.getInt(loadoutNumberColumn);
				playerLoadouts.put(loadoutNumber, new Loadout(playerName, loadoutNumber, resultSet.getString(primaryWeaponColumn), resultSet.getString(secondaryWeaponColumn), PerkHandler.getPerk(resultSet.getString(perkColumn))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
		return playerLoadouts;
	}

	public void insertLoadouts(Collection<Loadout> loadouts) {
		PreparedStatement preparedStatement = prepareStatement(insertLoadoutStatement);
		try {
			for (Loadout loadout : loadouts) {
				preparedStatement.setString(1, loadout.getPlayerName());
				preparedStatement.setInt(2, loadout.getNumber());
				preparedStatement.setString(3, loadout.getPrimary());
				preparedStatement.setString(4, loadout.getSecondary());
				preparedStatement.setString(5, loadout.getPerk().getPerkName());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
	}

	public void updateLoadout(String name, int loadout, String primary, String secondary, String perk) {
		PreparedStatement preparedStatement = prepareStatement(updateLoadoutStatement);
		try {
			//Set the variables required to update a players loadout
			preparedStatement.setString(1, primary);
			preparedStatement.setString(2, secondary);
			preparedStatement.setString(3, perk);
			preparedStatement.setString(4, name);
			preparedStatement.setInt(5, loadout);
			//Execute the update
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
	}
}
