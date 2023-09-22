package com.fruitSalad_backend.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.fruitSalad_backend.*"})
@EnableJpaRepositories(basePackages ={"com.fruitSalad_backend.*"})
@EntityScan(basePackages ={"com.fruitSalad_backend.*"})
public class PaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
