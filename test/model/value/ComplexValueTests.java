package model.value;

import static org.junit.Assert.assertEquals;
import generated.model.shared.Address;
import generated.model.shared.Person;

import org.junit.Test;

public class ComplexValueTests {
	private static final String ADDRESS = "address";
	private static final String STREET = "street";

	@Test
	public void test() throws Exception {
		Person p = new Person();
		Address a = new Address();
		p.setAddress(a);

		String street = "Elm";
		a.setStreet(street);

		assertEquals(street, p.<Address> get(ADDRESS).getStreet());
		assertEquals(street, p.<Address> get(ADDRESS).get(STREET));
		String valueResult = p.<Address> getValue(ADDRESS).get()
				.<String> getValue(STREET).get();
		assertEquals(street, valueResult);
		String result = p.<ComplexValue> get(ADDRESS).get(STREET);
		assertEquals(street, result);
	}

	@Test
	public void testMap() throws Exception {
		ComplexValue p = new MapComplexValue();
		ComplexValue a = new MapComplexValue();
		p.set(ADDRESS, a);

		String street = "Elm";
		a.set(STREET, street);

		String valueResult = p.<ComplexValue> getValue(ADDRESS).get()
				.<String> getValue(STREET).get();
		assertEquals(street, valueResult);
		String result = p.<ComplexValue> get(ADDRESS).get(STREET);
		assertEquals(street, result);
	}
}
