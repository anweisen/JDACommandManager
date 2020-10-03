package net.codingarea.botmanager.defaults.commands;

import net.codingarea.botmanager.commandmanager.CommandEvent;
import net.codingarea.botmanager.commandmanager.commands.Command;
import net.codingarea.botmanager.defaults.DefaultLogManager;
import net.codingarea.botmanager.utils.Replacement;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nonnull;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class DefaultSetLogCommand extends Command {

	private final DefaultLogManager logManager;

	public DefaultSetLogCommand(@Nonnull DefaultLogManager logManager) {
		this(logManager, "setlog");
	}

	public DefaultSetLogCommand(@Nonnull DefaultLogManager logManager, @Nonnull String name, @Nonnull String... alias) {
		super(name, Permission.ADMINISTRATOR, alias);
		this.logManager = logManager;
	}

	public DefaultSetLogCommand(@Nonnull DefaultLogManager logManager, @Nonnull String name, @Nonnull Permission permission, @Nonnull String... alias) {
		super(name, permission, alias);
		this.logManager = logManager;
	}

	@Override
	public void onCommand(@Nonnull final CommandEvent event) throws Throwable {

		if (event.getMentionedChannels().size() != 1) {
			sendSyntax(event, "<#channel>");
			return;
		}

		TextChannel channel = event.getFirstMentionedChannel();
		logManager.setLogChannel(channel);
		event.queueReply(getMessage(event, "log-set", "The log channel was set to %channel%", new Replacement("%channel%", channel.getAsMention())));

	}

}
