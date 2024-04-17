package ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ex")
@EntityScan(basePackages = "ex.entity")
@EnableJpaRepositories(basePackages = "ex.repository")
public class SpringBootSecurityWithDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityWithDbApplication.class, args);
		System.err.println("spring up");
	}

}
