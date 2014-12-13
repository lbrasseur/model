package model.gen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;

import model.type.Property;
import model.type.Type;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class Generator {
	@VisibleForTesting
	static final Pattern IGNORED_PACKAGES = Pattern.compile("^java\\..*");

	public static Iterable<SourceFile> generate(Iterable<Type> types) {
		checkNotNull(types);
		Map<Type, SourceFile> files = Maps.newHashMap();
		for (Type type : types) {
			typeTofile(type, files);
		}
		return files.values();
	}

	public static void write(Iterable<SourceFile> files, String dir) {
		checkNotNull(files);
		checkNotNull(dir);
		try {
			File baseDir = new File(dir);
			for (SourceFile file : files) {
				File fileDir = new File(baseDir, file.getDirectory());
				fileDir.mkdirs();
				Files.write(file.getContent(),
						new File(fileDir, file.getName()), Charsets.UTF_8);
			}
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	private static void typeTofile(final Type type, Map<Type, SourceFile> files) {
		if (!files.containsKey(type)
				&& !IGNORED_PACKAGES.matcher(type.getNamespace()).matches()) {
			StringBuilder content = new StringBuilder();

			append(content, "package %s;", type.getNamespace());
			append(content, "");

			append(content, "import model.value.JavaComplexValue;");
			append(content, "import model.value.Value;");

			Iterable<Type> typesToImport = Sets
					.newHashSet(Iterables.filter(Iterables.transform(
							type.getProperties(), Property.TO_TYPE),
							new Predicate<Type>() {
								@Override
								public boolean apply(Type aType) {
									return !aType.getNamespace().equals(
											type.getNamespace())
											&& !aType.getNamespace().equals(
													"java.lang");
								}
							}));
			Iterable<Type> sortedTypesToImport = Ordering.from(
					new Comparator<Type>() {
						@Override
						public int compare(Type o1, Type o2) {
							return o1.getNamespace().compareTo(
									o2.getNamespace());
						}
					}).sortedCopy(typesToImport);
			for (Type typeToImport : sortedTypesToImport) {
				append(content, "import %s.%s;", typeToImport.getNamespace(),
						typeToImport.getName());
			}
			append(content, "");

			append(content, "public class %s extends JavaComplexValue {", type.getName());
			for (Property property : type.getProperties()) {
				append(content,
						"  public final Value<%s> %s = new Value<%s>();",
						property.getType().getName(), property.getName(),
						property.getType().getName());
			}
			append(content, "");
			for (Property property : type.getProperties()) {
				append(content, "  public void set%s(%s %s) {",
						CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,
								property.getName()), property.getType()
								.getName(), property.getName());
				append(content, "    this.%s.set(%s);", property.getName(),
						property.getName());
				append(content, "  }");
				append(content, "");
				append(content, "  public %s get%s() {", property.getType()
						.getName(), CaseFormat.LOWER_CAMEL.to(
						CaseFormat.UPPER_CAMEL, property.getName()));
				append(content, "    return this.%s.get();", property.getName());
				append(content, "  }");
				append(content, "");
			}

			append(content, "}");

			files.put(type,
					new SourceFile(type.getName() + ".java", "/"
							+ type.getNamespace().replaceAll("\\.", "/"),
							content.toString()));

			for (Property property : type.getProperties()) {
				typeTofile(property.getType(), files);
			}
		}
	}

	private static void append(StringBuilder sb, String line, Object... args) {
		sb.append(String.format(line, args));
		sb.append('\n');
	}

	private Generator() {
		super();
	}
}
