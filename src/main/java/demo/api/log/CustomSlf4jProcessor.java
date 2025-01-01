package demo.api.log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("demo.api.log.CustomSlf4j")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class CustomSlf4jProcessor extends AbstractProcessor {

  private final Set<String> processedClasses = new HashSet<>();

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "CustomSlf4jProcessor is running");
    try {
      for (Element element : roundEnv.getElementsAnnotatedWith(CustomSlf4j.class)) {
//	if (element.getKind() != ElementKind.CLASS) {
//	  processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
//	      "@CustomLogger can only be applied to classes.");
//	  continue;
//	}
	String qualifiedClassName = ((TypeElement) element).getQualifiedName().toString();
	processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE,
	    "qualifiedClassName:" + qualifiedClassName);
	if (processedClasses.contains(qualifiedClassName)) {
	  continue; // 已处理过
	}
	processedClasses.add(qualifiedClassName);

	// 获取类名
	String className = element.getSimpleName().toString();
	processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "className:" + className);

	// 生成的代码
	String code = "private static final demo.api.log.CustomLogger customLogger = demo.api.log.CustomLoggerFactory.getLogger("
	    + className + ".class);";
	processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "code:" + code);

	// 获取编译路径
//	String classPath = processingEnv.getFiler().getClass().getProtectionDomain().getCodeSource().getLocation()
//	    .getPath();
//	String classPath = Paths.get("build", "classes", "java", "main").toAbsolutePath().toString();
	String classPath = "build/classes/java/main";
//	String classPath = "bin/main";
	processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, "classPath:" + classPath);

	ClassPool classPool = ClassPool.getDefault();
	Iterator<String> it = classPool.getImportedPackages();
	while (it.hasNext()) {
	  System.out.println(it.next());
	}
	classPool.insertClassPath(classPath);
	System.out.println("dddddddddddddddddddddd");

	// 加载目标类
	CtClass ctClass = classPool.get(qualifiedClassName);
	System.out.println("eeeeeeeeeeeeeeeeeeeeeeee");

	// 添加 logger 字段
	CtField loggerField = CtField.make(code, ctClass);

	ctClass.addField(loggerField);

	// 写回修改后的类
	ctClass.writeFile(classPath);
	ctClass.detach();
      }
    } catch (Exception e) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
	  "Failed to process @CustomSlf4j: " + e.getMessage());
    }
    return true;
  }
}
