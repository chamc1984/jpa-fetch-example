package xyz.chamc.jpafetchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import xyz.chamc.jpafetchexample.domain.PersistenceConfig;

@SpringBootApplication
@Import({ PersistenceConfig.class })
public class JpaFetchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaFetchExampleApplication.class, args);
	}
}
