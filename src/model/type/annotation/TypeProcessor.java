package model.type.annotation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.tools.JavaFileObject;

import model.gen.Generator;
import model.gen.SourceFile;
import model.type.Property;
import model.type.Type;

import com.google.common.base.Throwables;
import com.google.common.collect.Sets;

@SupportedAnnotationTypes("model.type.annotation.TypeDef")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TypeProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		saveTypes(getTypes(annotations, roundEnv));
		return true;
	}

	private Set<Type> getTypes(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Set<Type> types = Sets.newHashSet();
		for (TypeElement te : annotations) {
			for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
				if (e.getKind() == ElementKind.CLASS) {
					TypeElement classElement = (TypeElement) e;
					PackageElement packageElement = (PackageElement) classElement
							.getEnclosingElement();

					DeclaredType superClass = (DeclaredType) processingEnv
							.getTypeUtils()
							.directSupertypes(classElement.asType()).get(0);
					String superClassName = superClass.asElement()
							.getSimpleName().toString();

					TypeDef typeDef = classElement.getAnnotation(TypeDef.class);
					Type type = new Type(superClassName, packageElement
							.getQualifiedName().toString());

					type.setProperties(Arrays
							.asList(typeDef.properties())
							.stream()
							.map((PropertyDef propertyDef) -> new Property(
									propertyDef.name(), new Type(propertyDef
											.typeName(), propertyDef
											.typeNamespace())))::iterator);

					types.add(type);
				}

			}
		}
		return types;
	}

	private void saveTypes(Set<Type> types) {
		try {
			for (SourceFile source : Generator.generate(types)) {
				if (types.contains(source.getType())) {
					try {
						JavaFileObject jfo = processingEnv.getFiler()
								.createSourceFile(
										source.getType().getNamespace() + "."
												+ source.getType().getName());
						try (BufferedWriter bw = new BufferedWriter(
								jfo.openWriter())) {
							bw.append(source.getContent());
						}
					} catch (FilerException e) {
					}
				}
			}
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
}
