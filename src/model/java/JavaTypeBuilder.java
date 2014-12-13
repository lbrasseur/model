package model.java;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import model.type.Property;
import model.type.Type;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class JavaTypeBuilder {
	private static Map<Class<?>, Type> typeCache = Maps.newHashMap();

	private static final Function<Field, Property> PROPERTY_FROM_FIELD =
			(Field field) -> new Property(field.getName(),
			JavaTypeBuilder.fromJavaClass(field.getType()));

	public static Type fromJavaClass(Class<?> javaClass) {
		checkNotNull(javaClass);
		Type javaType = typeCache.get(javaClass);
		if (javaType == null) {
			synchronized (JavaTypeBuilder.class) {
				if (javaType == null) {
					javaType = new Type(javaClass.getSimpleName(),
							javaClass.getPackage() != null ? javaClass
									.getPackage().getName() : "");

					typeCache.put(javaClass, javaType);
					javaType.setProperties(Iterables.transform(
							Arrays.asList(javaClass.getDeclaredFields()),
							PROPERTY_FROM_FIELD));
				}
			}
		}
		return javaType;
	}

	private JavaTypeBuilder() {
	}
}
