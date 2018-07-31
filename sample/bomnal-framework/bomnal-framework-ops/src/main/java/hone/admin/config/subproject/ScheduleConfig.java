package hone.admin.config.subproject;

import hone.bom.modules.ModuleBeanFactoryWrapper;
import hone.bom.scheduling.quartz.HoneSchedulerFactoryBean;
import hone.bom.scheduling.quartz.SchedulerFactoryBeanPostProcessor;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *
 * <pre>
 *
 * DESC:스케줄러 컨피그
 *
 * </pre>
 *
 * @author 1072160
 */
@Configuration
public class ScheduleConfig implements BeanFactoryAware {

  private BeanFactory beanFactory;

  @Autowired(required = false)
  private ModuleBeanFactoryWrapper moduleBeanFactoryWrapper;

  public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    SchedulerFactoryBean factory = new HoneSchedulerFactoryBean();
    return factory;
  }

  @Bean
  public Scheduler scheduler() {
    return schedulerFactoryBean().getObject();
  }

  @Bean
  public SchedulerFactoryBeanPostProcessor schedulerFactoryBeanPostProcessor() {
    SchedulerFactoryBeanPostProcessor sfbpp = new SchedulerFactoryBeanPostProcessor();
    sfbpp.setCronShchedulingConfigLocation("classpath:op-schedule.json");
    if (this.moduleBeanFactoryWrapper != null) {
      sfbpp.setBeanFactory(this.moduleBeanFactoryWrapper);
    } else {
      sfbpp.setBeanFactory(this.beanFactory);
    }
    return sfbpp;
  }
}
