package pl.patrykkawula.autocare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoCareApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AutoCareApplication.class, args);
    }

}
