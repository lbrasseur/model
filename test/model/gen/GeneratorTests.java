package model.gen;

import java.lang.reflect.Field;

import model.java.JavaType;
import model.java.Person;
import model.type.Property;
import model.type.Type;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class GeneratorTests {
	private static final String GENERATED_TYPE_PREFIX = "generated.";

	@Test
	public void test() throws Exception {
		Type personType = JavaType.fromJavaClass(Person.class);
		changePackage(personType);
		Generator
				.write(Generator.generate(ImmutableList.of(personType)), "gen");
	}

	private void changePackage(Type type) throws Exception {
		if (!type.getNamespace().startsWith(GENERATED_TYPE_PREFIX)
				&& !Generator.IGNORED_PACKAGES.matcher(type.getNamespace())
						.matches()) {
			Field field = Type.class.getDeclaredField("namespace");
			field.setAccessible(true);
			field.set(type, GENERATED_TYPE_PREFIX + type.getNamespace());
			for (Property property : type.getProperties()) {
				changePackage(property.getType());
			}
		}
	}
}
