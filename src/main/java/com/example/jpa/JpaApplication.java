package com.example.jpa;

import com.example.jpa.models.Video;
import com.example.jpa.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(
//			VideoRepository videoRepository
//	) {
//		return args -> {
//			var video = Video.builder()
//					.name("abc")
//					.length(6)
//					.build();
//			videoRepository.save(video);
//		};
//	}
}
