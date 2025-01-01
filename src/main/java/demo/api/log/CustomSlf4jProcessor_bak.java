package demo.api.log;

import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("demo.api.log.CustomSlf4j")
@SupportedSourceVersion(SourceVersion.RELEASE_21) // 根据项目使用的 Java 版本调整
public class CustomSlf4jProcessor_bak extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "CustomSlf4jProcessor is running");

    for (Element element : roundEnv.getElementsAnnotatedWith(CustomSlf4j.class)) {
      if (element.getKind() == ElementKind.CLASS) {
	TypeElement typeElement = (TypeElement) element;
	String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
	String className = typeElement.getSimpleName().toString();
	String qualifiedClassName = packageName + "." + className;

	try {
	  // 生成新的类文件
	  JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(qualifiedClassName + "Logger");
	  processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
	      "Generating class for " + builderFile.getName());
	  try (Writer writer = builderFile.openWriter()) {
	    writer.write("package " + packageName + ";\n\n");
	    writer.write("import demo.api.log.CustomLogger;\n");
	    writer.write("import demo.api.log.CustomLoggerFactory;\n\n");
	    writer.write("public class " + className + "Logger" + " {\n");
	    writer.write("    public static final CustomLogger customLogger = CustomLoggerFactory.getLogger("
		+ className + ".class);\n");
	    writer.write("}\n");
	  }
	} catch (Exception e) {
	  processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
	      "Failed to process @CustomSlf4j: " + e.getMessage());
	}
      }
    }
    return true;
  }
}
