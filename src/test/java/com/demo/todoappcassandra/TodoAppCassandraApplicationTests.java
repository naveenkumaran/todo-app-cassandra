package com.demo.todoappcassandra;

import java.nio.file.Paths;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

class TodoAppCassandraApplicationTests {

    @Value("${spring.data.cassandra.username}")
    private String username;
    @Value("${spring.data.cassandra.password}")
    private String password;

    @Test
    @DisplayName("Test to connect Cassandra on cloud")
	void should_connect_to_cassandra() {
        try (CqlSession session = CqlSession.builder()
           .withCloudSecureConnectBundle(Paths.get("/tmp/cred.zip"))
           .withAuthCredentials("username","password")
           .build()) {
           ResultSet rs = session.execute("select release_version from system.local");
           Row row = rs.one();
           assert row != null;
	}

}
