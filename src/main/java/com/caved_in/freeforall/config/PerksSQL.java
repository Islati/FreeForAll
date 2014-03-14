package com.caved_in.freeforall.config;

import com.caved_in.commons.sql.SQL;
import com.caved_in.freeforall.perks.Perk;
import com.caved_in.freeforall.perks.PerkHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class PerksSQL extends SQL {

	private static String perkTable = "guns_perks";
	private static String playerColumn = "Player";
	private static String perkColumn = "Perk";

	private static String getDataStatement = "SELECT * FROM " + perkTable + " WHERE " + playerColumn + "=?";
	private static String insertDataStatement = "INSERT INTO " + perkTable + " (" + playerColumn + ", " + perkColumn + ") VALUES (?, ?)";
	private static String creationStatement = "CREATE TABLE IF NOT EXISTS `[DB]`.`guns_perks` (`ID` int(10) unsigned NOT NULL AUTO_INCREMENT, `Player` text NOT NULL, `Perk` text NOT NULL, PRIMARY KEY (`ID`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;";

	public PerksSQL(SqlConfiguration sqlConfig) {
		super(
				sqlConfig.getHost(),
				sqlConfig.getPort(),
				sqlConfig.getDatabase(),
				sqlConfig.getUsername(),
				sqlConfig.getPassword()
		);
		creationStatement = creationStatement.replace("[DB]", sqlConfig.getDatabase());
		execute(creationStatement);
	}

	public boolean hasData(String playerName) {
		PreparedStatement preparedStatement = prepareStatement(getDataStatement);
		boolean hasData = false;
		try {
			preparedStatement.setString(1, playerName);
			hasData = preparedStatement.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
		return hasData;
	}


	public Set<Perk> getPerks(String playerName) {
		Set<Perk> playerPerks = new HashSet<>();
		PreparedStatement preparedStatement = prepareStatement(getDataStatement);
		try {
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				playerPerks.add(PerkHandler.getPerk(resultSet.getString(perkColumn)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
		return playerPerks;
	}

	public void insertPerk(Perk perk, String playerName) {
		PreparedStatement preparedStatement = prepareStatement(insertDataStatement);
		try {
			preparedStatement.setString(1, playerName);
			preparedStatement.setString(2, perk.getPerkName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
	}
}
