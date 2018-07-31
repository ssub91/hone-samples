package bomnal.framework.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import hone.bom.web.simple.config.app.BaseConfig;
import hone.bom.web.simple.config.app.CommonConfig;
import hone.bom.web.simple.config.app.DbioConfig;
import hone.bom.web.simple.config.app.HoneIoConfig;
import hone.bom.web.simple.config.app.JasyptConfig;

@Configuration
@Import(value = {BaseConfig.class, CommonConfig.class, HoneIoConfig.class
		, JasyptConfig.class, DbioConfig.class})
@ImportResource({ "classpath*:/honeconfig/test/*.xml"})
public class TestConfig {

}
