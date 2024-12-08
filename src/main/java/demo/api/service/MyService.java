package demo.api.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {
  @Async
  public void asyncProcess() {
    // 模拟异步处理的耗时操作
    try {
      Thread.sleep(3000);
      System.out.println("异步处理完成");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void syncProcess() {
    // 模拟同步处理的耗时操作
    try {
      Thread.sleep(3000);
      System.out.println("同步处理完成");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
