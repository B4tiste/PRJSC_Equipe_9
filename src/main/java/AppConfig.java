import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories(basePackages = {"repositories", "repositories.type", "repositories.referentiel"})
@ComponentScan
public class AppConfig {


}