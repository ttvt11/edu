package com.edu.homepage.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//  히카리cp란? 커넥션 풀(api과 db연결할때 이를 효과적으로 관리하게 사용하는 라이브러리) 종류
// @PropertySource : 설정파일의 위치를 선정해주는 것.
@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

   @Autowired
   private ApplicationContext applicationContext;

   // @Bean : 설정파일 생성역할
   @Bean
   @ConfigurationProperties(prefix="spring.datasource.hikari")
   public HikariConfig hikariConfig() {
      return new HikariConfig();
   }

   @Bean
   @ConfigurationProperties(prefix="mybatis.configuration")
   public org.apache.ibatis.session.Configuration mybatisConfig(){
      return new org.apache.ibatis.session.Configuration();
   }

   //  히카리설정파일을 이용해 DB연결하는 데이터소스 설정
   @Bean
   public DataSource dataSource() throws Exception{
      DataSource dataSource = new HikariDataSource(hikariConfig());
      System.out.println(dataSource.toString());
      return dataSource;
   }

   // 마이바티스설정파일을 이용해서 sql문을 읽는 xml파일 경로 설정
   @Bean
   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(dataSource);
      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
      sqlSessionFactoryBean.setConfiguration(mybatisConfig());

      return sqlSessionFactoryBean.getObject();
   }

   @Bean
   public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
      return new SqlSessionTemplate(sqlSessionFactory);
   }
}
