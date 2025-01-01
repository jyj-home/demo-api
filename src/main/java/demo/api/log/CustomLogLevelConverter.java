package demo.api.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomLogLevelConverter extends ClassicConverter {
  @Override
  public String convert(ILoggingEvent event) {
    // 如果日志包含 "FATAL" 标记，则显示为 FATAL
    if (event.getMarkerList() != null && !event.getMarkerList().isEmpty()
	&& "FATAL".equals(event.getMarkerList().get(0).getName())) {
      return "FATAL";
    }
    // 否则保留原来的级别显示
    return event.getLevel().toString();
  }
}
