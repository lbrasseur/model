package model.type;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public class Type {
	private final String name;
	private final String namespace;
	private Map<String, Property> properties;

	public Type(String name, String namespace) {
		this.name = checkNotNull(name);
		this.namespace = checkNotNull(namespace);
	}

	public void setProperties(Iterable<Property> properties) {
		this.properties = Maps.newLinkedHashMap(Maps.uniqueIndex(properties,
				Property.TO_NAME));
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
}
