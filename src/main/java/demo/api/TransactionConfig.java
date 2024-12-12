package demo.api;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

  @Autowired
  private DataSource ds1DataSource;

  @Autowired
  private DataSource ds2DataSource;

  // 第一个数据源的事务管理器
  @Bean("ds1TransactionManager")
  @Primary
  public PlatformTransactionManager transactionManager1() {
    return new DataSourceTransactionManager(ds1DataSource);
  }

  // 第二个数据源的事务管理器
  @Bean("ds2TransactionManager")
  public PlatformTransactionManager transactionManager2() {
    return new DataSourceTransactionManager(ds2DataSource);
  }
}
