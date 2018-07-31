package bomnal.framework.online.config.app;

import java.text.MessageFormat;
import java.util.Locale;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import hone.bom.commons.message.BomMessage;
import hone.bom.commons.message.BomMessageSource;
import hone.bom.commons.message.MessageService;
import hone.bom.commons.message.MessageServiceHolder;
import hone.bom.commons.message.impl.DefaultMessageService;

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
 * - Gildong Hong(gildong) 2017. 8. 1. 초기작성
 * </pre>
 * @author Gildong Hong(gildong)
 *
 */
@Configuration
public class MessageConfig implements ApplicationContextAware {
    private ApplicationContext applicationContext;


    @Resource(name = "messageDataSource")
    private DataSource messageDataSource;


    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean(name = "messageService")
    public MessageService messageService() {
        DefaultMessageService dms = new DefaultMessageService(defaultMessageSource(), new Locale("ko", "KR"));
        return dms;
    }

    
    @Bean(name = "messageSource")
    public MessageSource defaultMessageSource() {
    	ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    	ms.setDefaultEncoding("UTF-8");
    	ms.setBasenames("classpath:honeconfig/i18n/messages/");
        return ms;
    }

    @Bean
    public MessageServiceHolder messageServiceHolder() {
        return new MessageServiceHolder();
    }

}