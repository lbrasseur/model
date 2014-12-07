package model.java;

import static org.junit.Assert.assertEquals;
import model.type.Type;

import org.junit.Test;

import com.google.common.collect.Iterables;

public class TypeTests {
	@Test
	public void test() {
		Type personType = JavaType.fromJavaClass(Person.class);

		assertEquals(personType.getName(), "Person");
		assertEquals(personType.getNamespace(), "model.java");
		assertEquals(Iterables.size(personType.getProperties()), 4);
		assertEquals(personType.getProperty("address").getType().getName(),
				"Address");
		assertEquals(personType.getProperty("father").getType(), personType);
	}
}
