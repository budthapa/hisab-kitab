package pro.budthapa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class HisabKitabApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisabKitabApplication.class, args);
	}
	
	@Bean
	@Description("Spring message resolver")
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		return messageSource;
	}
}
