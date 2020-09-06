package net.anweisen.commandmanager.defaults;

import net.anweisen.commandmanager.sql.SQL;
import net.anweisen.commandmanager.utils.Bindable;
import net.anweisen.commandmanager.utils.Factory;
import net.dv8tion.jda.api.entities.Guild;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Developed in the CommandManager project
 * on 09-06-2020
 *
 * @author anweisen | https://github.com/anweisen
 * @since 2.1
 */
public final class DefaultPrefixCache implements Factory<String, Guild>, Bindable {

	//                          guildID, prefix
	private final transient Map<String, String> cache = new HashMap<>();
	private boolean cachePrefix;

	private String defaultPrefix;

	private final String table, guildColumn, prefixColumn;
	private final SQL dataSource;

	public DefaultPrefixCache(boolean cachePrefix, @Nonnull String defaultPrefix, @Nonnull String table, @Nonnull String guildColumn, @Nonnull String prefixColumn, @Nonnull SQL dataSource) {
		this.cachePrefix = cachePrefix;
		this.defaultPrefix = defaultPrefix;
		this.table = table;
		this.guildColumn = guildColumn;
		this.prefixColumn = prefixColumn;
		this.dataSource = dataSource;
	}

	public String getCached(@Nonnull String guildID) {
		return cache.get(guildID);
	}

	public boolean isCached(@Nonnull String guildID) {
		return getCached(guildID) != null;
	}

	public synchronized void setCached(@Nonnull String guildID, String prefix) {
		if (!cachePrefix) throw new IllegalStateException();
		if (prefix == null) {
			cache.remove(guildID);
		} else {
			cache.put(guildID, prefix);
		}
	}

	public void setDefaultPrefix(@Nonnull String defaultPrefix) {
		this.defaultPrefix = defaultPrefix;
	}

	public String getDefaultPrefix() {
		return defaultPrefix;
	}

	public SQL getDataSource() {
		return dataSource;
	}

	public boolean shouldCachePrefix() {
		return cachePrefix;
	}

	public void setCachePrefix(boolean cache) {
		this.cachePrefix = cache;
		if (cache) this.cache.clear();
	}

	public String getFromDatabase(String guildID) throws SQLException {
		ResultSet result = dataSource.query("SELECT " + prefixColumn + " FROM " + table + " WHERE " + guildColumn + " = ?", guildID);
		result.next();
		String prefix = result.getString(prefixColumn);
		result.close();
		return prefix;
	}

	public String load(String guildID) {

		String saved = defaultPrefix;

		try {
			saved = getFromDatabase(guildID);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (cachePrefix) {
			setCached(guildID, saved);
		}

		return saved;

	}

	public synchronized void set(String guildID, String prefix) throws SQLException {
		if (cachePrefix) setCached(guildID, prefix);
		dataSource.update("UPDATE " + table + " SET " + prefixColumn + " = ? WHERE " + guildColumn + " = ?", prefix, guildID);
	}

	@Nonnull
	@Override
	public String get(Guild guild) {
		if (guild == null) {
			return defaultPrefix;
		} else {
			return load(guild.getId());
		}
	}

}