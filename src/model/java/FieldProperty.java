package model.java;

import java.lang.reflect.Field;

import model.type.Property;

import com.google.common.base.Function;

public class FieldProperty extends Property {
	static final Function<Field, Property> FROM_FIELD = new Function<Field, Property>() {
		@Override
		public Property apply(Field field) {
			return new FieldProperty(field);
		}
	};

	private FieldProperty(Field field) {
		super(field.getName(), JavaType.fromJavaClass(field.getType()));
	}
}
