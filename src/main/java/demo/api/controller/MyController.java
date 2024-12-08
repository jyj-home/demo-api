package demo.api.controller;

import demo.api.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  @Autowired
  private MyService myService;

  public void doSomething(boolean isAsync) {
    if (isAsync) {
      myService.asyncProcess();
    } else {
      myService.syncProcess();
    }
  }
}
