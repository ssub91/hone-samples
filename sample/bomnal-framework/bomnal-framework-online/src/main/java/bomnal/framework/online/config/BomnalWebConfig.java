package bomnal.framework.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import hone.bom.web.multipart.DownloadController;
import hone.bom.web.multipart.FileResourceResolver;
import hone.bom.web.multipart.UploadController;
import hone.bom.web.simple.config.web.WebBaseConfig;

@Configuration
@EnableWebMvc
@ImportResource({"classpath*:/honeconfig/web/*.xml"})
public class BomnalWebConfig extends WebBaseConfig {

	@Bean
	public UploadController uploadController() {
		UploadController uploadController = new UploadController();
		return uploadController;
	}
	
	@Bean
	public DownloadController downloadController() {
		DownloadController downloadController = new DownloadController();
		return downloadController;
	}
	
	@Bean
	public FileResourceResolver filResourceResolver() {
		FileResourceResolver fileResourceResolver = new FileResourceResolver();
		return fileResourceResolver;
	}
}
