package demo.api;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig1 {

  @Bean("ds1DataSource")
  @ConfigurationProperties(prefix = "spring.datasource.ds1")
  @Primary
  public DataSource dataSource1() {
    HikariConfig hikariConfig = new HikariConfig();
    // 根据配置属性设置参数，此处可以添加更多自定义逻辑
    hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.3.100:5432/db001");
    hikariConfig.setUsername("db_user001");
    hikariConfig.setPassword("dbuser001");
    hikariConfig.setDriverClassName("org.postgresql.Driver");
    hikariConfig.setConnectionTimeout(30000);
    hikariConfig.setMaximumPoolSize(10);
    hikariConfig.setMinimumIdle(5);
    return new HikariDataSource(hikariConfig);
//    return DataSourceBuilder.create().build();
//    return new HikariDataSource();
  }
}
