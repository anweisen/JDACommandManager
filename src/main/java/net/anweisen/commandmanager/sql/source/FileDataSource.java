package net.anweisen.commandmanager.sql.source;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * Developed in the CommandManager project
 * on 09-01-2020
 *
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class FileDataSource implements DataSource {

	private final File file;

	public FileDataSource(@Nonnull String path) {
		this(new File(path));
	}

	public FileDataSource(@Nonnull File file) {
		if (!file.getName().endsWith(".db")) throw new IllegalArgumentException("File is not a .db file");
		this.file = file;
	}

	@Nonnull
	@Override
	public String getURL() {
		return DataSource.LITESQL_URL + file.getPath();
	}

	@Nonnull
	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "FileDataSource{" +
				"file=" + file +
				'}';
	}
}
