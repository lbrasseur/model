package model.gen;

import static java.util.Objects.requireNonNull;
import model.type.Type;

public class SourceFile {
	private final String name;
	private final String directory;
	private final String content;
	private final Type type;

	public SourceFile(String name, String directory, String content, Type type) {
		this.name = requireNonNull(name);
		this.directory = requireNonNull(directory);
		this.content = requireNonNull(content);
		this.type = requireNonNull(type);
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

	public Type getType() {
		return type;
	}
}
