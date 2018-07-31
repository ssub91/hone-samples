package bomnal.framework.test.config;

import hone.bom.batch.support.BatchModuleBeanFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:/honeconfig/test/batch/*.xml"})
public class BatchTestConfig {
	@Bean
	public BatchModuleBeanFactory beanFactory() {
		BatchModuleBeanFactory beanFactory =  new BatchModuleBeanFactory();
		beanFactory.setConfigLocation("/honeconfig/test/modules/batch-modules-context.xml");
		return beanFactory;
	}
}
