package model.value;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.google.common.collect.Maps;

public class MapComplexValue extends ComplexValue {
	private final Map<String, Value<?>> values;

	public MapComplexValue() {
		this.values = Maps.newHashMap();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Value<T> getValue(String name) {
		checkNotNull(name);
		if (!values.containsKey(name)) {
			synchronized (this) {
				if (!values.containsKey(name)) {
					values.put(name, new Value<T>());
				}
			}
		}
		return (Value<T>) values.get(name);
	}
}
