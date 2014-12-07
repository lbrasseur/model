package model.java;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Map;

import model.type.Type;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class JavaType extends Type {
	private static Map<Class<?>, JavaType> typeCache = Maps.newHashMap();

	public static JavaType fromJavaClass(Class<?> javaClass) {
		checkNotNull(javaClass);
		JavaType javaType = typeCache.get(javaClass);
		if (javaType == null) {
			synchronized (JavaType.class) {
				if (javaType == null) {
					javaType = new JavaType(javaClass);

					typeCache.put(javaClass, javaType);
					javaType.setProperties(Iterables.transform(
							Arrays.asList(javaClass.getDeclaredFields()),
							FieldProperty.FROM_FIELD));
				}
			}
		}
		return javaType;
	}

	private JavaType(Class<?> javaClass) {
		super(javaClass.getSimpleName(),
				javaClass.getPackage() != null ? javaClass.getPackage()
						.getName() : "");
	}
}
