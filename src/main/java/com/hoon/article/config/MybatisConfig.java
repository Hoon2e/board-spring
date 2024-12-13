package com.hoon.article.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

//@Configuration
public class MybatisConfig {
	 /**
     * SqlSessionFactory 빈 생성
     * 
     * 
     * @param dataSource 데이터베이스 연결을 위한 DataSource 객체
     * @return SqlSessionFactoryBean 객체
     * @throws Exception 예외 처리
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource); // 데이터 소스 설정
        // MyBatis 설정 파일 위치 지정
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 반환
    }

    /**
     * SqlSessionTemplate 빈 생성
     * 
     * 
     * @param sqlSessionFactory SqlSessionFactory 객체
     * @return SqlSessionTemplate 인스턴스
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory); // SqlSessionTemplate 반환
    }
}
