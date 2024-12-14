package demo.api;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataSourceLogger {

  private final DataSource ds1DataSource;
  private final DataSource ds2DataSource;

  public DataSourceLogger(@Qualifier("ds1DataSource") DataSource ds1DataSource,
      @Qualifier("ds2DataSource") DataSource ds2DataSource) {
    this.ds1DataSource = ds1DataSource;
    this.ds2DataSource = ds2DataSource;
  }

  @PostConstruct
  public void logDataSourceDetails() {
    System.out.println("Logging DataSource Details...");

    // 检查 ds1 DataSource
    if (ds1DataSource instanceof HikariDataSource) {
      HikariDataSource hikarids1 = (HikariDataSource) ds1DataSource;
      System.out.println("ds1 DataSource - HikariCP Configuration:");
      System.out.println("JDBC URL: " + hikarids1.getJdbcUrl());
      System.out.println("Username: " + hikarids1.getUsername());
      System.out.println("Maximum Pool Size: " + hikarids1.getMaximumPoolSize());
      System.out.println("Connection Timeout: " + hikarids1.getConnectionTimeout());
      System.out.println("Idle Timeout: " + hikarids1.getIdleTimeout());
      System.out.println("Max Lifetime: " + hikarids1.getMaxLifetime());
      System.out.println("Auto Commit: " + hikarids1.isAutoCommit());
    } else {
      System.out.println("ds1 DataSource is not an instance of HikariDataSource.");
    }

    // 检查 ds2 DataSource
    if (ds2DataSource instanceof HikariDataSource) {
      HikariDataSource hikarids2 = (HikariDataSource) ds2DataSource;
      System.out.println("ds2 DataSource - HikariCP Configuration:");
      System.out.println("JDBC URL: " + hikarids2.getJdbcUrl());
      System.out.println("Username: " + hikarids2.getUsername());
      System.out.println("Maximum Pool Size: " + hikarids2.getMaximumPoolSize());
      System.out.println("Connection Timeout: " + hikarids2.getConnectionTimeout());
      System.out.println("Idle Timeout: " + hikarids2.getIdleTimeout());
      System.out.println("Max Lifetime: " + hikarids2.getMaxLifetime());
      System.out.println("Auto Commit: " + hikarids2.isAutoCommit());
    } else {
      System.out.println("ds2 DataSource is not an instance of HikariDataSource.");
    }
  }
}
