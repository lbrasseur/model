package model.java;

import static org.junit.Assert.assertEquals;
import model.shared.Person;
import model.type.Type;

import org.junit.Test;

import com.google.common.collect.Iterables;

public class TypeTests {
	@Test
	public void test() {
		Type personType = JavaTypeBuilder.fromJavaClass(Person.class);

		assertEquals("Person", personType.getName());
		assertEquals("model.shared", personType.getNamespace());
		assertEquals(4, Iterables.size(personType.getProperties()));
		assertEquals("Address", personType.getProperty("address").getType()
				.getName());
		assertEquals(personType, personType.getProperty("father").getType());
	}
}
