package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	void varTest() {
		var str = "hello";
		var list = new ArrayList<>();
		IntStream.range(1, 100).forEach(list::add);
		assertThat(list).hasSize(99);
	}

	@Test
	void collectionTest() {
		var list = List.of("1", "2", "3");
		List<String> copyList = List.copyOf(list);
		assertThat(copyList).isEqualTo(list);

		var list1 = new ArrayList<String>();
		list1.add("123");
		list1.add("456");
		List<String> unmodifiableList = list1.stream().collect(Collectors.toUnmodifiableList());
		assertThrows(UnsupportedOperationException.class, ()->{
			unmodifiableList.add("123");
		});

		Set<String> unmodifiableSet = list1.stream().collect(Collectors.toUnmodifiableSet());
		assertThrows(UnsupportedOperationException.class, ()->{
			unmodifiableSet.add("123");
		});
	}

	@Test
	void optionalTest() {
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(10L)
				.build();
		cache.put("1", "Hello");
		cache.put("2", "World");
		assertThrows(IllegalArgumentException.class, ()->{
			Optional.ofNullable(cache.getIfPresent("3"))
					.orElseThrow(IllegalArgumentException::new);
		});
	}


}