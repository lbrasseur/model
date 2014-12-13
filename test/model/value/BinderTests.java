package model.value;

import static org.junit.Assert.assertEquals;
import generated.model.shared.Person;

import java.util.Date;

import org.junit.Test;

import com.google.common.base.Strings;

public class BinderTests {

	@Test
	public void test() throws Exception {
		Person a = new Person();
		Person b = new Person();

		a.name.asObservable()
			.map((String s) -> Strings.nullToEmpty(s))
			.map((String s) -> s.toUpperCase())
			.subscribe(b.name.asSubscriber());
		a.birthday.asObservable()
			.subscribe(b.birthday.asSubscriber());

		a.setName("Pepe");
		Date now = new Date();
		a.setBirthday(now);

		assertEquals("PEPE", b.getName());
		assertEquals(now, b.getBirthday());
	}

	@Test
	public void testComplexValue() throws Exception {
		Person a = new Person();
		Person b = new Person();

		a.<String>getValue("name").asObservable()
			.map((String s) -> Strings.nullToEmpty(s))
			.map((String s) -> s.toUpperCase())
			.subscribe(b.getValue("name").asSubscriber());
		a.getValue("birthday").asObservable()
			.subscribe(b.getValue("birthday").asSubscriber());

		a.setName("Pepe");
		Date now = new Date();
		a.setBirthday(now);

		assertEquals("PEPE", b.getName());
		assertEquals(now, b.getBirthday());
	}
	
	@Test
	public void testMap() throws Exception {
		ComplexValue a = new MapComplexValue();
		ComplexValue b = new MapComplexValue();

		a.<String>getValue("name").asObservable()
			.map((String s) -> Strings.nullToEmpty(s))
			.map((String s) -> s.toUpperCase())
			.subscribe(b.getValue("name").asSubscriber());
		a.getValue("birthday").asObservable()
			.subscribe(b.getValue("birthday").asSubscriber());

		a.set("name", "Pepe");
		Date now = new Date();
		a.set("birthday", now);

		assertEquals("PEPE", b.get("name"));
		assertEquals(now, b.get("birthday"));
	}
}
