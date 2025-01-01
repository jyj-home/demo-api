package demo.api.log;

import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.TypeSpec;

@SupportedAnnotationTypes("demo.api.log.CustomSlf4j")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class CustomSlf4jProcessor_bak1 extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "CustomSlf4jProcessor is running");
    for (Element element : roundEnv.getElementsAnnotatedWith(CustomSlf4j.class)) {
      String className = element.getSimpleName().toString();
      String packageName = processingEnv.getElementUtils().getPackageOf(element).toString();

      FieldSpec loggerField = FieldSpec
	  .builder(ClassName.get("demo.api.log", "CustomLogger"), "customLogger",
	      javax.lang.model.element.Modifier.PRIVATE, javax.lang.model.element.Modifier.STATIC,
	      javax.lang.model.element.Modifier.FINAL)
	  .initializer("demo.api.log.CustomLoggerFactory.getLogger($L.class)", className).build();

      TypeSpec newClass = TypeSpec.classBuilder(className)
//                    .addModifiers(javax.lang.model.element.Modifier.PUBLIC)
	  .addField(loggerField).build();

      JavaFile javaFile = JavaFile.builder(packageName, newClass).build();
      try {
	javaFile.writeTo(processingEnv.getFiler());
      } catch (IOException e) {
	e.printStackTrace();
      }
    }
    return true;
  }
}
