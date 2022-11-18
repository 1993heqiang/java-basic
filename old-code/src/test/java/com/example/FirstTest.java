package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * FirstTest
 *
 * @author HeQiang
 * @since 2022/11/18
 **/
class FirstTest {

	@Test
	void hello() {
		Assertions.assertTrue(ExampleTest.isPrime(2));
	}

}
