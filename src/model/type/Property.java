package model.type;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

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
}
