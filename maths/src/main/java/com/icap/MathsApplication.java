package com.icap;

import com.icap.config.Validation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Validation.class)
public class MathsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathsApplication.class, args);
	}
}
