package bomnal.framework.online.config;

import hone.bom.dao.paging.DefaultPagingQueryBuilderFactory;
import hone.bom.dao.paging.PagingQueryBuilderFactory;
import hone.bom.web.simple.config.app.AsyncConfig;
import hone.bom.web.simple.config.app.BaseConfig;
import hone.bom.web.simple.config.app.CommonConfig;
import hone.bom.web.simple.config.app.DbioConfig;
import hone.bom.web.simple.config.app.HoneIoConfig;
import hone.bom.web.simple.config.app.JasyptConfig;
import hone.bom.web.simple.config.app.MonitorConfig;
import hone.bom.web.simple.config.app.RequestHandlerConfig;
import hone.bom.web.simple.config.app.TxServiceConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import bomnal.framework.online.config.app.MessageConfig;
import bomnal.framework.online.config.app.WebSecurityConfig;

@Configuration
@Import(value = {BaseConfig.class,
		CommonConfig.class,
		MessageConfig.class,
		HoneIoConfig.class,
		DbioConfig.class,
		TxServiceConfig.class,
		AsyncConfig.class,
		JasyptConfig.class,
		MonitorConfig.class,
		RequestHandlerConfig.class,
		WebSecurityConfig.class})
@ImportResource({ "classpath*:/honeconfig/app/*.xml", "classpath*:/honeconfig/ws/*.xml" })
public class BomnalAppConfig {

	@Bean
	public PagingQueryBuilderFactory pagingQueryBuilderFactory() {
		return new DefaultPagingQueryBuilderFactory();
	}
}
