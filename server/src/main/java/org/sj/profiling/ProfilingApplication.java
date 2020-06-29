package org.sj.profiling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfilingApplication {

	public static void main(String[] args) {
		FormModelGeneration.generateFormModels();
		SpringApplication.run(ProfilingApplication.class, args);
	}

}
