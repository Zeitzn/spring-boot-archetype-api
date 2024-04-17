package com.archetype.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class ArchetypeApiApplication implements CommandLineRunner {
	@Autowired
	private ApplicationContext appContext;
	public static void main(String[] args) {
		SpringApplication.run(ArchetypeApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		serverInfo();
	}
	private void serverInfo() {
		Environment env = this.appContext.getEnvironment();
		String protocol = "http";
		String port = env.getProperty("server.port");
		log.info("\n--------------------------------------------------------\n\tAPI '{}' is running! \n\tLocal: \t\t{}://localhost:{}\n\tProfile(s): \t{}\n--------------------------------------------------------", new Object[]{env.getProperty("spring.application.name"), protocol, port, env.getActiveProfiles()});
	}
}
