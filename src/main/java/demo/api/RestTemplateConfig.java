package demo.api;

import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate() {

    // 创建连接池管理器
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    // 设置最大连接数，这里设置为100，你可以根据实际需求调整
    connectionManager.setMaxTotal(100);
    // 设置每个路由的最大连接数，比如针对某个特定的服务端域名的最大连接数
    connectionManager.setDefaultMaxPerRoute(20);

    // 配置请求配置对象，用于设置各种超时时间等参数
    RequestConfig requestConfig = RequestConfig.custom()
    // 设置连接超时时间（单位：毫秒），这里示例设为5000，可按需调整
//	.setConnectTimeout(5000, TimeUnit.MILLISECONDS)
	// 设置连接请求超时时间（单位：毫秒），这里设为3000，可按需改变
	.setConnectionRequestTimeout(3000, TimeUnit.MILLISECONDS)
	// 设置读取超时时间（单位：毫秒），此处设为8000，你可根据实际业务场景等调整
	.setResponseTimeout(8000, TimeUnit.MILLISECONDS).build();

    ConnectionConfig connectionConfig = ConnectionConfig.custom().setConnectTimeout(5000, TimeUnit.MILLISECONDS)
	.build();
    connectionManager.setDefaultConnectionConfig(connectionConfig);

    // 使用HttpClientBuilder构建HttpClient实例，并设置连接池和请求配置
    CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
	.setDefaultRequestConfig(requestConfig).build();
    // 创建HttpComponentsClientHttpRequestFactory，将HttpClient实例传入
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    return new RestTemplate(requestFactory);
  }
}
