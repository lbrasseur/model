package model.value;

public class JavaComplexValue extends ComplexValue {
	@SuppressWarnings("unchecked")
	@Override
	public <T> Value<T> getValue(String name) {
		try {
			return ((Value<T>) getClass().getDeclaredField(name).get(this));
		} catch (IllegalAccessException | NoSuchFieldException
				| SecurityException e) {
			throw new IllegalArgumentException("Invalid field: " + name, e);
		}
	}
}
