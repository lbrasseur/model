package model.annotation;

import model.type.annotation.PropertyDef;
import model.type.annotation.TypeDef;

@TypeDef(properties = {
		@PropertyDef(name = "name", typeName = "String", typeNamespace = "java.lang")
		})
public class Brand extends SuperBrand {

}
