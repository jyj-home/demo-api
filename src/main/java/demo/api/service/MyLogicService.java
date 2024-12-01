package demo.api.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyLogicService {
  public String processRequestHeaders() {
    // 先从RequestContextHolder中获取请求属性对象
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
      HttpServletRequest request = servletRequestAttributes.getRequest();
      // 通过HttpServletRequest获取特定头部信息，比如获取Authorization头部
      String authorizationHeader = request.getHeader("Authorization");
      System.out.println("Authorization头部信息: " + authorizationHeader);

      // 也可以遍历所有头部信息
      Enumeration<String> headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
	String headerName = headerNames.nextElement();
	String headerValue = request.getHeader(headerName);
	System.out.println("头部字段名: " + headerName + ", 头部字段值: " + headerValue);
      }
      return "已处理请求头部信息";
    }
    return "无法获取请求相关属性";
  }
}
