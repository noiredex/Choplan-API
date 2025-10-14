package com.choplan.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.choplan.payment.config.PortOneProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = PortOneProperties.class)
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

}
