package net.codingarea.engine.utils;

import net.codingarea.engine.utils.function.Factory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.3
 */
public final class DefaultFactory {

	private DefaultFactory() { }

	@Nonnull
	@CheckReturnValue
	public static <T> Factory<String, T> objectToString() {
		return String::valueOf;
	}

	@Nonnull
	@CheckReturnValue
	public static <T> Factory<Integer, T> objectToInt() {
		return NumberConversions::toInt;
	}

	@Nonnull
	@CheckReturnValue
	public static <T extends ISnowflake> Factory<String, T> mentionableToID() {
		return ISnowflake::getId;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<Integer, String> stringToInteger() {
		return Integer::valueOf;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<Double, String> stringToDouble() {
		return Double::valueOf;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, String> stringToString() {
		return string -> string;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<Role, String> stringToRole(Guild guild) {
		return guild::getRoleById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<TextChannel, String> stringToTextChannel(Guild guild) {
		return guild::getTextChannelById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<TextChannel, String> stringToTextChannel(ShardManager shardManager) {
		return shardManager::getTextChannelById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<VoiceChannel, String> stringToVoiceChannel(Guild guild) {
		return guild::getVoiceChannelById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<Member, String> stringToMember(Guild guild) {
		return guild::getMemberById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<User, String> stringToUser(ShardManager shardManager) {
		return shardManager::getUserById;
	}

	@Nonnull
	@CheckReturnValue
	public static <T extends GuildChannel> Factory<String, T> guildChannelToName() {
		return GuildChannel::getName;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, IMentionable> mentionableToMention() {
		return IMentionable::getAsMention;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, Enum<?>> enumToName() {
		return Enum::name;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<Integer, Enum<?>> enumToOrdinal() {
		return Enum::ordinal;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, Role> roleToName() {
		return Role::getName;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, Class<?>> classToSimpleName() {
		return Class::getSimpleName;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<String, Class<?>> classToName() {
		return Class::getName;
	}

	@Nonnull
	@CheckReturnValue
	public static <T extends INamed> Factory<String, T> namedToName() {
		return INamed::getName;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<GuildChannel, String> stringToGuildChannel(final @Nonnull Guild guild) {
		return guild::getGuildChannelById;
	}

	@Nonnull
	@CheckReturnValue
	public static Factory<GuildChannel, String> stringToGuildChannel(final @Nonnull JDA jda) {
		return jda::getGuildChannelById;
	}

}
