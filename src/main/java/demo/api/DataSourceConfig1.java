package demo.api;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig1 {

  // 配置数据源1属性读取
  @Bean("ds1DataSourceProperties")
  @ConfigurationProperties(prefix = "spring.datasource.ds1")
  public DataSourceProperties dataSource1Properties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean("ds1DataSource")
  @ConfigurationProperties(prefix = "spring.datasource.ds1.hikari")
  public DataSource dataSource1(@Qualifier("ds1DataSourceProperties") DataSourceProperties dataSourceProperties) {
//    HikariConfig hikariConfig = new HikariConfig();
//    // 根据配置属性设置参数，此处可以添加更多自定义逻辑
//    hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.3.100:5432/db001");
//    hikariConfig.setUsername("db_user001");
//    hikariConfig.setPassword("dbuser001");
//    hikariConfig.setDriverClassName("org.postgresql.Driver");
//    hikariConfig.setConnectionTimeout(30000);
//    hikariConfig.setMaximumPoolSize(10);
//    hikariConfig.setMinimumIdle(5);
//    return new HikariDataSource();

    return dataSourceProperties.initializeDataSourceBuilder().build();
  }
}
