package demo.api;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig2 {

  @Bean("ds2DataSource")
  @ConfigurationProperties(prefix = "spring.datasource.ds2.hikari")
  public DataSource dataSource2() throws SQLException {
//    HikariConfig hikariConfig = new HikariConfig();hikariConfig.
//    // 根据配置属性设置参数，此处可以添加更多自定义逻辑
//    hikariConfig.setJdbcUrl("jdbc:postgresql://192.168.3.100:5432/db001");
//    hikariConfig.setUsername("db_user001");
//    hikariConfig.setPassword("dbuser001");
//    hikariConfig.setDriverClassName("org.postgresql.Driver");
//    hikariConfig.setConnectionTimeout(30000);
//    hikariConfig.setMaximumPoolSize(10);
//    hikariConfig.setMinimumIdle(5);
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}
