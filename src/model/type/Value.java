package model.type;

import javax.annotation.Nullable;

public class Value<T> {
	private T value;

	@Nullable
	public T get() {
		return value;
	}

	public void set(@Nullable T value) {
		this.value = value;
	}

	public boolean isNull() {
		return value == null;
	}
}
