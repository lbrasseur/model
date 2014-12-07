package model.type;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;
import com.google.common.base.Objects;

public class Property {
	static final Function<Property, String> TO_NAME = new Function<Property, String>() {
		@Override
		public String apply(Property property) {
			return property.getName();
		}
	};

	public static final Function<Property, Type> TO_TYPE = new Function<Property, Type>() {
		@Override
		public Type apply(Property property) {
			return property.getType();
		}
	};

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
		return Objects.hashCode(name, type);
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
		return Objects.equal(name, that.name) && Objects.equal(type, that.type);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Property.class).add("name", name)
				.add("type", type).toString();
	}
}
