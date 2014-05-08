package com.caved_in.freeforall.config;

import com.caved_in.commons.sql.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
public class GunsSQL extends DatabaseConnector {
	private static String gunsTable = "Guns_Weapons";
	private static String playerColumn = "Player";
	private static String gunColumn = "GunID";
	private static String getDataStatement = "SELECT * FROM " + gunsTable + " WHERE " + playerColumn + "=?";
	private static String insertDataStatement = "INSERT IGNORE INTO " + gunsTable + " (" + playerColumn + ", " + gunColumn + ") VALUES (?,?)";
	private static String createTableStatement = "CREATE TABLE IF NOT EXISTS `[DB]`.`Guns_Weapons` (`ID` int(10) unsigned NOT NULL AUTO_INCREMENT, `Player` text NOT NULL, `GunID` text NOT NULL, PRIMARY KEY (`ID`) ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;";

	private static Set<String> playersWithData = new HashSet<>();

	public GunsSQL(SqlConfiguration sqlConfig) {
		super(
				sqlConfig.getHost(),
				sqlConfig.getPort(),
				sqlConfig.getDatabase(),
				sqlConfig.getUsername(),
				sqlConfig.getPassword()
		);
		createTableStatement = createTableStatement.replace("[DB]", sqlConfig.getDatabase());
	//	execute(createTableStatement);
	}

	public boolean hasData(String playerName) {
		boolean hasData = playersWithData.contains(playerName);
		if (!hasData) {
			PreparedStatement preparedStatement = prepareStatement(getDataStatement);
			try {
				preparedStatement.setString(1, playerName);
				hasData = preparedStatement.executeQuery().next();
				playersWithData.add(playerName);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(preparedStatement);
			}
		}
		return hasData;
	}

	public Set<String> getGuns(String playerName) {
		Set<String> guns = new HashSet<>();
		PreparedStatement preparedStatement = prepareStatement(getDataStatement);
		try {
			preparedStatement.setString(1, playerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				guns.add(resultSet.getString(gunColumn));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
		return guns;
	}

	public boolean hasGun(String playerName, String gunId) {
		return getGuns(playerName).contains(gunId);
	}

	public void insertGuns(String playerName, Collection<String> gunIds) {
		PreparedStatement preparedStatement = prepareStatement(insertDataStatement);
		try {
			for (String gun : gunIds) {
				preparedStatement.setString(1, playerName);
				preparedStatement.setString(2, gun);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
		}
	}

	public void insertGun(String playerName, String gunId) {
		if (!hasGun(playerName, gunId)) {
			PreparedStatement preparedStatement = prepareStatement(insertDataStatement);
			try {
				preparedStatement.setString(1, playerName);
				preparedStatement.setString(2, gunId);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(preparedStatement);
			}
		}
	}
}
