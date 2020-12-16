package com.demo.todoappcassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;

@SpringBootApplication
// (exclude = { CassandraDataAutoConfiguration.class, CassandraAutoConfiguration.class })
public class TodoAppCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppCassandraApplication.class, args);
	}

    @Bean
    // TODO: Can I do this
    @CrossOrigin(
        methods = { POST, GET, PATCH, DELETE }
    )
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
        .route(GET("/welcome"), request -> { return ServerResponse.ok().body("Hello World"); })
        .andRoute(GET("/uuid/{uuid}"), (request) -> { throw new RuntimeException("Method not implemented"); });
    }

    @Configuration
    public class ApplicationConfig {

        @Value("${datastax.astra.cloud-secure-bundle}")
        private String cloudSecureBundle;

        @Bean
        public CqlSessionBuilderCustomizer sessionBuilder() {
            return builder -> builder.withCloudSecureConnectBundle(Paths.get(cloudSecureBundle))
        }
    }
    
}
