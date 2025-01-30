package com.sgds.optimisation_trajet_dechets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* @EnableJpaRepositories("com.sgds.optimisation_trajet_dechets.Repository")
@EntityScan("com.sgds.optimisation_trajet_dechets.Model")
@ComponentScan("com.sgds.optimisation_trajet_dechets.Controller") */

public class OptimisationTrajetDechetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimisationTrajetDechetsApplication.class, args);
	}

}
