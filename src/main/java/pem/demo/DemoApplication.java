package pem.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pem.demo.domain.clustering.ClusteringService;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
