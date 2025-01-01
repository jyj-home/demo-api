package demo.api.log;

public class CustomLoggerFactory {

  private CustomLoggerFactory() {
// 処理なし
  }

  // 静态工厂方法
  public static CustomLogger getLogger(Class<?> clazz) {
    return new CustomLogger(clazz);
  }
}
