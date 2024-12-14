package demo.api;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration(proxyBeanMethods = false)
//@EnableTransactionManagement
public class DataSourceConfig {

  // 配置 Primary 数据源
  @Primary
  @Bean(name = "primaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.primary")
  public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  // 配置 Secondary 数据源
  @Bean(name = "secondaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.secondary")
  public DataSource secondaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  // 配置 Primary 事务管理器
  @Primary
  @Bean(name = "primaryTransactionManager")
  public PlatformTransactionManager primaryTransactionManager(
      @Qualifier("primaryDataSource") DataSource primaryDataSource) {
    return new DataSourceTransactionManager(primaryDataSource);
  }

  // 配置 Secondary 事务管理器
  @Bean(name = "secondaryTransactionManager")
  public PlatformTransactionManager secondaryTransactionManager(
      @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
    return new DataSourceTransactionManager(secondaryDataSource);
  }

  // 配置 Primary SqlSessionFactory
  @Primary
  @Bean(name = "primarySqlSessionFactory")
  public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource)
      throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(primaryDataSource);
    return factoryBean.getObject();
  }

  // 配置 Secondary SqlSessionFactory
  @Bean(name = "secondarySqlSessionFactory")
  public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource secondaryDataSource)
      throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(secondaryDataSource);
    return factoryBean.getObject();
  }

  // 配置 Primary SqlSessionTemplate
  @Primary
  @Bean(name = "primarySqlSessionTemplate")
  public SqlSessionTemplate primarySqlSessionTemplate(
      @Qualifier("primarySqlSessionFactory") SqlSessionFactory primarySqlSessionFactory) {
    return new SqlSessionTemplate(primarySqlSessionFactory);
  }

  // 配置 Secondary SqlSessionTemplate
  @Bean(name = "secondarySqlSessionTemplate")
  public SqlSessionTemplate secondarySqlSessionTemplate(
      @Qualifier("secondarySqlSessionFactory") SqlSessionFactory secondarySqlSessionFactory) {
    return new SqlSessionTemplate(secondarySqlSessionFactory);
  }
}
