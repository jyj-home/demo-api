package demo.api;

//@Configuration
//@ConfigurationProperties(prefix = "spring.http.client")
public class RestTemplateConfiguration1 {
//  private int connectTimeout;
//  private int responseTimeout;
//  private int poolMaxConnections;
//  private int poolMaxConnectionsPerRoute;
//  private int connectionRequestTimeout;
//
//  public void setConnectTimeout(int connectTimeout) {
//    this.connectTimeout = connectTimeout;
//  }
//
//  public void setResponseTimeout(int responseTimeout) {
//    this.responseTimeout = responseTimeout;
//  }
//
//  public void setPoolMaxConnections(int poolMaxConnections) {
//    this.poolMaxConnections = poolMaxConnections;
//  }
//
//  public void setPoolMaxConnectionsPerRoute(int poolMaxConnectionsPerRoute) {
//    this.poolMaxConnectionsPerRoute = poolMaxConnectionsPerRoute;
//  }
//
//  public void setConnectionRequestTimeout(int connectionRequestTimeout) {
//    this.connectionRequestTimeout = connectionRequestTimeout;
//  }
//
//  @Bean
//  public RestTemplate restTemplate() {
//    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//    connectionManager.setMaxTotal(poolMaxConnections);
//    connectionManager.setDefaultMaxPerRoute(poolMaxConnectionsPerRoute);
//    connectionManager.setDefaultConnectionConfig(
//	ConnectionConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(connectTimeout)).build());
//
//    RequestConfig requestConfig = RequestConfig.custom().setResponseTimeout(Timeout.ofMilliseconds(responseTimeout))
//	.setConnectionRequestTimeout(Timeout.ofMilliseconds(connectionRequestTimeout)).build();
//
//    CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
//	.setDefaultRequestConfig(requestConfig).build();
//
//    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//    return new RestTemplate(requestFactory);
//  }
}
