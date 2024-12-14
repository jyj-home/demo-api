package demo.api;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("deprecation")
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

  // 第一个数据源的事务管理器
  @Primary
  @Bean("ds1TransactionManager")
  PlatformTransactionManager transactionManager1(@Qualifier("ds1DataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  // 第二个数据源的事务管理器
  @Bean("ds2TransactionManager")
  PlatformTransactionManager transactionManager2(@Qualifier("ds2DataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "chainedTransactionManager")
  ChainedTransactionManager chainedTransactionManager(
      @Qualifier("ds1TransactionManager") DataSourceTransactionManager ds1TransactionManager,
      @Qualifier("ds2TransactionManager") DataSourceTransactionManager ds2TransactionManager) {
    return new ChainedTransactionManager(ds1TransactionManager, ds2TransactionManager);
  }
}
