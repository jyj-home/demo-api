package demo.api.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {

  private final Logger logger;

  protected CustomLogger(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public void info(String message, Object... args) {
    logger.info(formatMessage(message), args);
  }

  public void error(String message, Object... args) {
    logger.error(formatMessage(message), args);
  }

  public void debug(String message, Object... args) {
    logger.debug(formatMessage(message), args);
  }

  public void warn(String message, Object... args) {
    logger.warn(formatMessage(message), args);
  }

  private String formatMessage(String message) {
    // 在这里添加统一的前缀或上下文信息
    return String.format("[CustomLog] %s", message);
  }
}
