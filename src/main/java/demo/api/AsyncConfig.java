package demo.api;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  @Bean("asyncExecutor")
  ThreadPoolTaskExecutor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // 设置核心线程数
    executor.setCorePoolSize(10);
    // 设置最大线程数
    executor.setMaxPoolSize(20);
    // 设置队列容量
//    executor.setQueueCapacity(Integer.MAX_VALUE);
    // 设置线程名前缀
    executor.setThreadNamePrefix("Async-Thread-");
    // 设置拒绝策略为DiscardOldestPolicy
    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
    executor.setRejectedExecutionHandler(handler);
    // 初始化线程池
    executor.initialize();
    return executor;
  }
}