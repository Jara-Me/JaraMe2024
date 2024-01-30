package siliconDream.jaraMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //커밋 전 삭제?
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //커밋 전 삭제?
public class JaraMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaraMeApplication.class, args);
	}

}
