package com.anish.opensphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OpensphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpensphereApplication.class, args);
	}

}
