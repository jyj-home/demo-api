package demo.api;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig2 {

  // 配置数据源2属性读取
  @Bean("ds2DataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.ds2")
  public DataSourceProperties dataSource1Properties() {
    return new DataSourceProperties();
  }

  @Bean("ds2DataSource")
  @ConfigurationProperties(prefix = "spring.datasource.ds2.hikari")
  public DataSource dataSource2(@Qualifier("ds2DataSourceProperties") DataSourceProperties dataSourceProperties) {
//    HikariConfig hikariConfig = new HikariConfig();hikariConfig.
//    // 根据配置属性设置参数，此处可以添加更多自定义逻辑
//    hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.3.100:5432/db001");
//    hikariConfig.setUsername("db_user001");
//    hikariConfig.setPassword("dbuser001");
//    hikariConfig.setDriverClassName("org.postgresql.Driver");
//    hikariConfig.setConnectionTimeout(30000);
//    hikariConfig.setMaximumPoolSize(10);
//    hikariConfig.setMinimumIdle(5);
    return dataSourceProperties.initializeDataSourceBuilder().build();
  }
}
