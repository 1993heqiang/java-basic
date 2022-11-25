package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.JedisPool;

/**
 * TestcontainersTest
 *
 * @author HeQiang
 * @since 2022/11/25
 **/
@Testcontainers
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
		System.err.println(redis.getExposedPorts());

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

		private JedisPool jedisPool;

		public RedisBackedCache(String host, int port) {
			this.jedisPool = new JedisPool(host, port);
		}

		public String get(String key) {
			return jedisPool.getResource().get(key);
		}

		public String put(String key, String value) {
			return jedisPool.getResource().set(key, value);
		}
	}
}
