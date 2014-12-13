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

		a.name.asObserver()
			.map((String s) -> Strings.nullToEmpty(s))
			.map((String s) -> s.toUpperCase())
			.subscribe(b.name.asSubscriber());
		a.birthday.asObserver()
			.subscribe(b.birthday.asSubscriber());

		a.setName("Pepe");
		Date now = new Date();
		a.setBirthday(now);

		assertEquals("PEPE", b.getName());
		assertEquals(now, b.getBirthday());
	}
}
