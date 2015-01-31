package model.annotation;

import model.type.annotation.PropertyDef;
import model.type.annotation.TypeDef;

@TypeDef(properties = {
		@PropertyDef(name = "brand", typeName = "Brand", typeNamespace = "model.annotation"),
		@PropertyDef(name = "price", typeName = "Double", typeNamespace = "java.lang") })
public class Car extends SuperCar {
	public String getBrandName() {
		return brand.get().name.get();
	}
}
