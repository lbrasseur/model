package model.gen;

import static com.google.common.base.Preconditions.checkNotNull;

public class SourceFile {
	private final String name;
	private final String directory;
	private final String content;

	public SourceFile(String name, String directory, String content) {
		this.name = checkNotNull(name);
		this.directory = checkNotNull(directory);
		this.content = checkNotNull(content);
	}

	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

	public String getContent() {
		return content;
	}
}
