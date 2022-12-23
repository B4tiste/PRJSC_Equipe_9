package pompier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@ComponentScan("Controllers")

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
