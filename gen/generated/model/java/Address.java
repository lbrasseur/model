package generated.model.java;

import model.type.Value;

public class Address {
  public final Value<String> street = new Value<String>();
  public final Value<Person> owner = new Value<Person>();

  public void setStreet(String street) {
    this.street.set(street);
  }

  public String getStreet() {
    return this.street.get();
  }

  public void setOwner(Person owner) {
    this.owner.set(owner);
  }

  public Person getOwner() {
    return this.owner.get();
  }

}
