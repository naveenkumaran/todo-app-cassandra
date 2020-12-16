package com.datastax.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;

@SpringBootApplication(exclude = { CassandraDataAutoConfiguration.class, CassandraAutoConfiguration.class })
public class TodoAppCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppCassandraApplication.class, args);
	}

}
