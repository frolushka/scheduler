package mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SchedulerApplication {

	public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
	}

}
