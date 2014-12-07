package model.binder;

import static org.junit.Assert.assertEquals;
import generated.model.shared.Person;

import java.util.Date;

import model.bind.Binder;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Functions;

public class BinderTests {

	@Test
	public void test() throws Exception {
		Person a = new Person();
		Person b = new Person();

		Binder.bind(a.name, b.name, new Function<String, String>() {
			@Override
			public String apply(String name) {
				return name != null ? name.toUpperCase() : null;
			}
		});
		Binder.bind(a.birthday, b.birthday, Functions.<Date> identity());

		a.setName("Pepe");
		Date now = new Date();
		a.setBirthday(now);

		assertEquals("PEPE", b.getName());
		assertEquals(now, b.getBirthday());
	}
}
