package demo.api;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "demo.api.gen2.repository", sqlSessionFactoryRef = "ds2SqlSessionFactory")
public class MyBatisConfig2 {

  @Bean("ds2SqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("ds2DataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
//    bean.setMapperLocations(
//	new PathMatchingResourcePatternResolver().getResources("classpath:demo/api/gen2/repository/*.xml"));
    return bean.getObject();
  }

  @Bean("ds2SqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ds2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
