package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * MainTest
 *
 * @author HeQiang
 * @since 2022/11/19
 **/
@ExtendWith(OutputCaptureExtension.class)
class FeatureTest {

	@Test
	void collectionNewApiTest() {
		List<String> list = List.of("Java", "C++");
		assertCollectionElement(list);
		assertThrows(UnsupportedOperationException.class, () -> {
			list.add("Hello");
		});

		Set<String> set = Set.of("Java", "C++");
		assertCollectionElement(set);

		Map<String, Integer> map = Map.of("Java", 1, "C++", 2);
		assertCollectionElement(map.keySet());

		Map<Integer, List<String>> result = list.stream()
				.collect(Collectors.groupingBy(String::length,
						Collectors.filtering(s -> !s.contains("z"),
								Collectors.toList())));

		System.out.println(result); // {1=[x], 2=[yy], 3=[www]}
	}

	private void assertCollectionElement(Collection<String> collection) {
		assertEquals(2, collection.size());
		assertTrue(collection.contains("Java"));
		assertTrue(collection.contains("C++"));
	}

	@Test
	void streamTest() {
//		Stream 中增加了新的方法 ofNullable()、dropWhile()、takeWhile() 以及 iterate() 方法的重载方法
		Stream<String> stream = Stream.ofNullable("Hello");
		assertEquals(1, stream.count());

		List<Integer> list = List.of(11, 33, 66, 8, 9, 13);
		List<Integer> dropWhile = list.stream().dropWhile(num -> num < 50)
				.collect(Collectors.toList());
		assertEquals(4, dropWhile.size());

		List<Integer> takeWhile = list.stream().takeWhile(num -> num < 50)
				.collect(Collectors.toList());
		assertEquals(2, takeWhile.size());

		List<String> strings = Stream.iterate("1", s -> s.length() < 20, s -> s + "1")
				.collect(Collectors.toList());
		assertEquals(19, strings.size());
	}

	@Test
	void optionalTest(CapturedOutput output) {
//		Optional 类中新增了 ifPresentOrElse()、or() 和 stream() 等方法
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("C++");
		Optional.of(list).ifPresentOrElse(strings -> strings.remove("Java"),
				() -> System.err.println("error"));
		assertEquals(1, list.size());

		Optional<Object> emptyOptional = Optional.empty();
		emptyOptional.ifPresentOrElse(System.out::println,
				() -> System.err.println("error"));
		assertThat(output).contains("error");

		emptyOptional.or(() -> Optional.of("java")).ifPresent(System.out::println);
		assertThat(output).contains("java");

		List<String> hello = Optional.of("Hello").stream().collect(Collectors.toList());
		assertThat(hello).hasSize(1).element(0).isEqualTo("Hello");
	}

	@Test
	void processHandleTest(CapturedOutput output) {
		ProcessHandle current = ProcessHandle.current();
		System.out.println(current.pid());
		System.out.println(current.info());
		assertThat(output).contains(String.valueOf(current.pid()));
	}

	@Test
	void interfaceMethodTest(CapturedOutput output) {
		MyCustom instance = new MyCustom();
		instance.handle();
		assertThat(output).contains("private method");
	}


}

interface MyInterface {

	private void methodPrivate() {
		System.out.println("Interface private method");
	}

	default void handle() {
		methodPrivate();
	}
}

class MyCustom implements MyInterface {

}