package net.anweisen.commandmanager.utils;

import org.jetbrains.annotations.NotNull;

import java.io.Writer;

/**
 * This class uses a {@link StringBuilder} to write to it every time called the {@link #write(char[], int, int)} method
 * @see StringBuilder
 * @author anweisen | https://github.com/anweisen
 * @since 2.1
 */
public class StringBuilderWriter extends Writer {

	protected final StringBuilder builder = new StringBuilder();

	@Override
	public void write(@NotNull char[] cbuf, int off, int len) {
		builder.append(cbuf, off, len);
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	@Override
	public void flush() { }

	@Override
	public void close() { }
}
