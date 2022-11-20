package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * FeatureTest
 *
 * @author HeQiang
 * @since 2022/11/19
 **/
@ExtendWith(OutputCaptureExtension.class)
class FeatureTest {

	@Test
	void stringTest() {
		// 判断字符串是否为空
		boolean isBlank = " ".isBlank();
		assertTrue(isBlank);

		// 去除字符串首尾空格
		String stripStr = " Java ".strip();
		assertEquals("Java", stripStr);

		// 去除字符串首部空格
		String stripLeading = " Java".stripLeading();
		assertEquals("Java", stripLeading);

		// 去除字符串尾部空格
		String stripTrailing = "Java ".stripTrailing();
		assertEquals("Java", stripTrailing);

		// 重复字符串多少次
		String repeatStr = "Java".repeat(3);
		assertEquals("JavaJavaJava", repeatStr);

		// 返回由行终止符分隔的字符串集合。
		long count = "A\nB\nC".lines().count();
		assertEquals(3, count);

		List<String> stringList = "A\nB\nC".lines().collect(Collectors.toList());
		assertThat(stringList).isEqualTo(List.of("A", "B", "C"));
	}

	@Test
	void optionalTest() {
		var op = Optional.empty();
		assertTrue(op.isEmpty());
	}

	@Test
	void httpClintTest(CapturedOutput output) throws IOException, InterruptedException {
		var request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.baidu.com"))
				.GET()
				.build();
		var client = HttpClient.newHttpClient();

		// 同步
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		assertEquals(200, response.statusCode());

		// 异步
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(s -> assertThat(s).isNotBlank());
	}

	@Test
	void varTest(CapturedOutput output) {
		Consumer<String> consumer = System.out::println;
		consumer.accept("123");
		assertThat(output).contains("123");
	}

}