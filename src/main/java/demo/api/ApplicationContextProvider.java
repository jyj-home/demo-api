package demo.api;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** XXXXXXXXXXXXX. */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
  // Spring应用上下文
  private static ApplicationContext applicationContext;
  private static Object objLocker = new Object();

  // 当Spring容器创建该类的实例时，会自动调用此方法，注入应用上下文
  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    synchronized (objLocker) {
      ApplicationContextProvider.applicationContext = context;
    }
  }

  // 提供一个静态方法，返回应用上下文
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  // 提供一个获取Bean的静态方法
  public static <T> T getBean(Class<T> beanClass) {
    return applicationContext.getBean(beanClass);
  }

  /**
   * 获取配置文件中的属性值.
   *
   * @param key 属性名
   * @return String 属性值
   */
  public static String getConfigValue(String key) {
    return applicationContext.getEnvironment().getProperty(key);
  }
}
