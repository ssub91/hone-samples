package bomnal.framework.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ImportResource("classpath*:/honeconfig/web/*.xml")
public class BomnalBatchWebConfig extends WebMvcConfigurerAdapter{
    
    @Bean(name="batchJobTaskExecutor")
    public ThreadPoolTaskExecutor batchJobTaskExecutor() {
        ThreadPoolTaskExecutor batchJobTaskExecutor = new ThreadPoolTaskExecutor();
        batchJobTaskExecutor.setThreadNamePrefix("batch-");
        batchJobTaskExecutor.setCorePoolSize(100);
        batchJobTaskExecutor.setMaxPoolSize(100);
        batchJobTaskExecutor.setQueueCapacity(100);
        return batchJobTaskExecutor;
    }
    
//    
//    @Bean
//    public ModuleReloadController moduleReloadController() {
//        return new ModuleReloadController();
//    }
}