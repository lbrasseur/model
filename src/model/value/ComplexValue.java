package model.value;

public abstract class ComplexValue {
	public <T> T get(String name) {
		return this.<T> getValue(name).get();
	}

	public <T> void set(String name, T value) {
		this.<T> getValue(name).set(value);
	}

	public abstract <T> Value<T> getValue(String name);
}
