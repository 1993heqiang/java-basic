package com.example.algorithm.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * BubbleSortTest
 *
 * @author HeQiang
 * @since 2022/11/18
 **/
class BubbleSortTest {

	@Test
	void smokeTest() {
		int[] source = IntStream.range(1, 100).toArray();
		List<Integer> list = Arrays.stream(source).boxed().collect(Collectors.toList());
		Collections.shuffle(list);
		int[] shuffleArr = list.stream().mapToInt(value -> value).toArray();
		assertArrayEquals(source, BubbleSort.sort(shuffleArr));
	}

}