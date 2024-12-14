package demo.api;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = "demo.api.gen.repository", sqlSessionFactoryRef = "ds1SqlSessionFactory")
public class MyBatisConfig1 {

  @Primary
  @Bean("ds1SqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
//    bean.setMapperLocations(
//	new PathMatchingResourcePatternResolver().getResources("classpath:demo/api/gen/repository/*.xml"));
    return bean.getObject();
  }

  @Primary
  @Bean("ds1SqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
