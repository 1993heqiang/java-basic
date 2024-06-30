package com.example.testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * TestcontainersTest
 *
 * @author HeQiang
 * @since 2022/11/25
 **/
@Testcontainers
@DisabledIfSystemProperty(named = "docker.enabled", matches = "false")
class TestcontainersTest {
	private RedisBackedCache underTest;

	@Container
	public GenericContainer redis = new GenericContainer(
			DockerImageName.parse("redis:5.0.3-alpine"))
			.withExposedPorts(6379);


	@BeforeEach
	public void setUp() {
		String address = redis.getHost();
		Integer port = redis.getFirstMappedPort();

		// Assume that we have Redis running locally?
		underTest = new RedisBackedCache(address, port);
	}

	@Test
	void testSimplePutAndGet() {
		underTest.put("test", "example");
		String retrieved = underTest.get("test");
		assertThat(retrieved).isEqualTo("example");
	}

	private static class RedisBackedCache {

		private Jedis jedis;

		public RedisBackedCache(String host, int port) {
			this.jedis = new Jedis(host, port);
			this.jedis.connect();
		}

		public String get(String key) {
			return jedis.get(key);
		}

		public String put(String key, String value) {
			return jedis.set(key, value);
		}
	}
}
