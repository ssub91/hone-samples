package bomnal.framework.test;

import hone.bom.context.ApplicationContextHolder;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import bomnal.framework.test.config.BatchTestConfig;
import bomnal.framework.test.config.TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class, BatchTestConfig.class})
public class AbstractBatchTest {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	protected ObjectMapper objectMapper;
	
	public AbstractBatchTest() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	
	protected DataSource getDataSource(){
		return dataSource;
	}
	
	protected JdbcOperations getJdbcOperations() {
		return jdbcOperations;
	}
	
	protected <T> T getBean(Class<T> requiredType) {
		return ApplicationContextHolder.getApplicationContext().getBean(requiredType);
	}	

}