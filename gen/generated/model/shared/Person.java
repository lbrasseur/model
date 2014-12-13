package generated.model.shared;

import model.value.JavaComplexValue;
import model.value.Value;

import java.util.Date;

public class Person extends JavaComplexValue {
	public final Value<String> name = new Value<String>();
	public final Value<Address> address = new Value<Address>();
	public final Value<Person> father = new Value<Person>();
	public final Value<Date> birthday = new Value<Date>();

	public void setName(String name) {
		this.name.set(name);
	}

	public String getName() {
		return this.name.get();
	}

	public void setAddress(Address address) {
		this.address.set(address);
	}

	public Address getAddress() {
		return this.address.get();
	}

	public void setFather(Person father) {
		this.father.set(father);
	}

	public Person getFather() {
		return this.father.get();
	}

	public void setBirthday(Date birthday) {
		this.birthday.set(birthday);
	}

	public Date getBirthday() {
		return this.birthday.get();
	}

}
