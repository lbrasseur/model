package model.value;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.annotation.Nullable;

import model.type.Type;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;

public class Value<T> {
	private T value;
	private Set<Runnable> changeHandlers;

	public Value() {
		changeHandlers = Sets.newHashSet();
	}

	@Nullable
	public T get() {
		return value;
	}

	public void set(@Nullable T value) {
		T oldValue = this.value;
		this.value = value;
		if (!Objects.equal(oldValue, value)) {
			for (Runnable changeHandler : changeHandlers) {
				changeHandler.run();
			}
		}
	}

	public boolean isNull() {
		return value == null;
	}

	public Registration addChangeHandler(final Runnable handler) {
		checkNotNull(handler);
		changeHandlers.add(handler);
		return new Registration() {
			@Override
			public void remove() {
				changeHandlers.remove(handler);
			}
		};
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Value<T> that = (Value<T>) o;
		return Objects.equal(value, that.value);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Type.class).add("value", value)
				.toString();
	}
}
