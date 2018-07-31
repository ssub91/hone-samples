package bomnal.framework.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import hone.bom.batch.support.BatchModuleBeanFactory;
import hone.bom.web.simple.config.app.BaseConfig;
import hone.bom.web.simple.config.app.CommonConfig;
import hone.bom.web.simple.config.app.DbioConfig;
import hone.bom.web.simple.config.app.HoneIoConfig;
import hone.bom.web.simple.config.app.JasyptConfig;

/**
 * {논리명}
 * 
 * <p>
 * {설명}
 * 
 * <p>
 * <b>변경이력:</b>
 * 
 * <pre>
 * - Gildong Hong(gildong) 2017. 9. 15. 초기작성
 * </pre>
 * @author Gildong Hong(gildong)
 *
 */
@Configuration
@Import(value = {BaseConfig.class, CommonConfig.class, HoneIoConfig.class
                 , JasyptConfig.class, DbioConfig.class})
@ImportResource({"classpath*:/honeconfig/batch/*.xml"})
public class BomnalBatchConfig {
    
    @Bean
    public BatchModuleBeanFactory beanFactory() {
        BatchModuleBeanFactory beanFactory =  new BatchModuleBeanFactory();
        beanFactory.setConfigLocation("/honeconfig/modules/batch-modules-context.xml");
        return beanFactory;
    }

}
