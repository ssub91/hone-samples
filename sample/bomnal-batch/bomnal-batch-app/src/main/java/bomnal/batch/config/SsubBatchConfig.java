package bomnal.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bomnal.batch.example.task.SsubTasklet;

@Configuration
@EnableBatchProcessing
public class SsubBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilders;

	@Autowired
	private StepBuilderFactory stepBuilders;

	/*
	 * appContext-batch-repository.xml
	 * 
	 * <bean id="jobRepository" class=
	 * "org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean"
	 * lazy-init="true"> <property name="transactionManager" ref="NoneTxManager" />
	 * </bean>
	 * 
	 * 대체
	 */

	@Bean
	public JobRepository jobRepository(ResourcelessTransactionManager txManager) throws Exception {
		return new MapJobRepositoryFactoryBean(txManager).getObject();
	}

	@Bean
	public JobRegistry jobRegistry() throws Exception {
		return new MapJobRegistry();
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) throws Exception {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	// for testing
	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils(JobRepository jobRepository) throws Exception {
		JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
		return jobLauncherTestUtils;
	}
	
	@Bean("ssubJob")
	public Job SsubJob(Step step) {
		return jobBuilders.get("ssubJob").start(step).build();
	}

	/*
	@Bean("itemReader")
	public ItemReader<PersonVo> reader() {
		return new SsubItemReader();
	}

	@Bean("itemWriter")
	public ItemWriter<PersonVo> writer() {
		return new SsubItemWriter<PersonVo>();
	}
	*/

	@Bean("ssubTasklet")
	public Tasklet tasklet() {
		return new SsubTasklet();
	}

	@Bean("ssubStep")
	public Step step(@Qualifier("ssubTasklet") Tasklet tasklet) {
		return stepBuilders.get("ssubStep").tasklet(tasklet).build();
	}
}
