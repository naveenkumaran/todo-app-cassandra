package com.demo.todoappcassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { CassandraDataAutoConfiguration.class, CassandraAutoConfiguration.class })
public class TodoAppCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppCassandraApplication.class, args);
	}

}
