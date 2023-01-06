package emergency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = {"emergency.repositories"})
@EntityScan(basePackages = {"emergency.models", "emergency.models.sensorRelated"})
@ComponentScan(basePackages = {
		"emergency.controllers", "emergency.services", "emergency.services.type", "emergency.services.referentiel", "emergency.repositories", "emergency.repositories.type", "emergency.repositories.referentiel",
		"emergency.modelDto", "emergency.modelDto.sensorRelated", "emergency.mapper", "emergency.baseReferentiel"
})
public class Application {


	public static void main(
			String[] args
	) {
		System.setProperty("server.servlet.context-path", "/UrgenceManager");
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/hello-world")
	public String hello() {
		return String.format("Hello World !");
	}
}
