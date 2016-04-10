package com.icap;

import com.icap.config.Swagger;
import com.icap.config.Validation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import({Validation.class, Swagger.class})
public class MathsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathsApplication.class, args);
	}
}
