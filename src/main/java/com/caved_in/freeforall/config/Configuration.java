package com.caved_in.freeforall.config;

import com.caved_in.freeforall.Game;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class Configuration {

	private SpawnConfiguration spawnConfiguration;

	private GunShopConfiguration gunShopConfiguration;

	private SqlConfiguration sqlConfiguration;

	private Serializer serializer = new Persister();

	public Configuration() {
	}

	public SpawnConfiguration getSpawnConfiguration() {
		return spawnConfiguration;
	}

	public GunShopConfiguration getGunShopConfiguration() {
		return gunShopConfiguration;
	}

	public SqlConfiguration getSqlConfiguration() {
		return sqlConfiguration;
	}

	public void saveConfig() {
		try {
			serializer.write(spawnConfiguration, new File(Game.SPAWN_CONFIG_FILE));
			serializer.write(gunShopConfiguration, new File(Game.GUN_CONFIG_FILE));
			serializer.write(sqlConfiguration, new File(Game.SQL_CONFIG_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Configuration init() {
		try {
			spawnConfiguration = serializer.read(SpawnConfiguration.class, new File(Game.SPAWN_CONFIG_FILE));
			gunShopConfiguration = serializer.read(GunShopConfiguration.class, new File(Game.GUN_CONFIG_FILE));
			sqlConfiguration = serializer.read(SqlConfiguration.class, new File(Game.SQL_CONFIG_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
}
