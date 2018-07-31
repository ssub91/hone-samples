package hone.admin.config.subproject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages={"krma.framework.admin"}
	, excludeFilters = @ComponentScan.Filter (value=Controller.class, type =FilterType.ANNOTATION))
@Import(value = {ScheduleConfig.class})
public class HmpConfig {

}
