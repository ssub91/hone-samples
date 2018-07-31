package bomnal.framework.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:/honeconfig/test/online/*.xml"})
public class OnlineTestConfig {
	
}
