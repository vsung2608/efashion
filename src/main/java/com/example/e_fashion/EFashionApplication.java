package com.example.e_fashion;

import com.example.e_fashion.entity.Role;
import com.example.e_fashion.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Async
public class EFashionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EFashionApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if(roleRepository.findByName("ADMIN").isEmpty()){
				roleRepository.save(
						Role.builder().name("ADMIN").build()
				);
			}
		};
	}

}
