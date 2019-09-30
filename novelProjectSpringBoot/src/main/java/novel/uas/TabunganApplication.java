package novel.uas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class TabunganApplication {
	public static void main(String[] arg) {
		SpringApplication.run(TabunganApplication.class, arg);
	}
}
