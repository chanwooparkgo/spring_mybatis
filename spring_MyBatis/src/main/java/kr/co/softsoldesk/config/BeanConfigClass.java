package kr.co.softsoldesk.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import kr.co.softsoldesk.mapper.MapperInterface;

@Configuration
@ComponentScan(basePackages = {"kr.co.softsoldesk.beans"})

public class BeanConfigClass {
	//data source
	@Bean
	public BasicDataSource   source() {
		BasicDataSource source =new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1522:system");
		source.setUsername("system");
		source.setPassword("12345");
		
		return source;
	}
	
	// mybatis => sql sessionFactory : jdbc를 처리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		
		//sqlsessionfactory에 대한 객체를 만들기위해서 sqlsessionfactoryBean
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		
		return factory;
	}
	
	
	@Bean //결제,장바구니 등 서비스이용에필요한기능
	public MapperFactoryBean<MapperInterface> test_mapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<MapperInterface> factoryBean = new MapperFactoryBean<MapperInterface>(MapperInterface.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean; //MapperInterface 최종서비스받는곳
	}
	
}
