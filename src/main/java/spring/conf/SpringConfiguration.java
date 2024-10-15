package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:spring/db.properties")  // 프로퍼티 파일 위치 지정
@MapperScan("user.dao")
public class SpringConfiguration {
	private @Value("${jdbc.driver}")String driver;
	private @Value("${jdbc.url}")String url;
	private @Value("${jdbc.username}")String username;
	private @Value("${jdbc.password}")String password;

    @Autowired
    private ApplicationContext context;
    
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    	sqlSessionFactoryBean.setDataSource(dataSource());
    	sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
    	//1개
    	//sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mapper/userMapper.xml"));
    	
//    	//Mapper.xml 파일이 2개 이상일 때	 -> 배열
//    	sqlSessionFactoryBean.setMapperLocations(
//    			new ClassPathResource("mapper/userMapper.xml"),
//    			new ClassPathResource("mapper/userUploadMapper.xml"));
    	
    	sqlSessionFactoryBean.setMapperLocations(
    			context.getResources("classpath:mapper/*Mapper.xml"));
    	
    	
    	//sqlSessionFactoryBean.setTypeAliasesPackage("user.bean");	//TypeAliase
    	sqlSessionFactoryBean.setTypeAliasesPackage("*.bean");	//bean 패키지가 여러 개일 때
    	//user.bean.UserDTO => userDTO
    	
    	return sqlSessionFactoryBean.getObject();	//SqlSessionFactory 변수
    }
    
     @Bean
     public SqlSessionTemplate sqlsession() throws Exception {
    	 SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
    	 return sqlSessionTemplate;
     }
     @Bean
     public DataSourceTransactionManager transcationManager() {
    	 DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
    	 return dataSourceTransactionManager;
     }
}
