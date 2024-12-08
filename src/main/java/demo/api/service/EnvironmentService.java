package demo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {
  @Autowired
  private Environment environment;

  public void printConfig() {
    int serverPort = Integer.parseInt(environment.getProperty("server.port"));
    String contextPath = environment.getProperty("server.servlet.context - path");
    System.out.println("Server Port: " + serverPort + ", Context Path: " + contextPath);
  }
}
