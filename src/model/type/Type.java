package model.type;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public class Type {
	private final String name;
	private final String namespace;
	private final Map<String, Property> properties;

	public Type(String name, String namespace) {
		this.name = checkNotNull(name);
		this.namespace = checkNotNull(namespace);
		this.properties = Maps.newLinkedHashMap();
	}

	/**
	 * Set after construction to avoid graph cycles.
	 */
	public void setProperties(Iterable<Property> properties) {
		this.properties.putAll(Maps.uniqueIndex(properties, Property.TO_NAME));
	}

	public String getName() {
		return name;
	}

	public String getNamespace() {
		return namespace;
	}

	public Iterable<Property> getProperties() {
		return properties.values();
	}

	@Nullable
	public Property getProperty(String name) {
		checkNotNull(name);
		return properties.get(name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, namespace, properties);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Type that = (Type) o;
		return Objects.equals(name, that.name)
				&& Objects.equals(namespace, that.namespace)
				&& Objects.equals(properties, that.properties);
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", namespace=" + namespace
				+ ", properties=" + properties + "]";
	}
}
