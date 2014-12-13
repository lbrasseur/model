package model.type;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

import com.google.common.base.Function;

public class Property {
	static final Function<Property, String> TO_NAME = (Property property) -> property
			.getName();

	public static final Function<Property, Type> TO_TYPE = (Property property) -> property
			.getType();

	private final String name;
	private final Type type;

	public Property(String name, Type type) {
		this.name = checkNotNull(name);
		this.type = checkNotNull(type);
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	@Override
	public int hashCode() {
		// Not including type to avoid infinite cycles.
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Property that = (Property) o;
		return Objects.equals(name, that.name)
				&& Objects.equals(type, that.type);
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", type=" + type + "]";
	}
}
