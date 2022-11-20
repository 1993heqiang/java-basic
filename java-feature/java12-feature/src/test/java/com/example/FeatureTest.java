package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;
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
	void stringTest() {
		String text = "Java";
		// 缩进 4 格
		text = text.indent(4);
		assertEquals("    Java\n", text);
		text = text.indent(-2);
		assertEquals("  Java\n", text);

		String foo = "foo";
		Integer len = foo.transform(String::length);
		assertEquals(3, len);
	}

	@Test
	void fileTest() throws IOException {
		Path filePath1 = Files.createTempFile("file1", ".txt");
		Path filePath2 = Files.createTempFile("file2", ".txt");
		Files.writeString(filePath1, "Java 12 Article");
		Files.writeString(filePath2, "Java 13 Article");

		long mismatch = Files.mismatch(filePath1, filePath2);
		assertEquals(6L, mismatch);
	}

	@Test
	void numberFormatTest() {
		NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US,
				Style.SHORT);
		String result = fmt.format(1000);
		assertEquals("1K", result);

		NumberFormat currencyFmt = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String currencyResult = currencyFmt.format(100000);
		assertEquals("¥100,000.00", currencyResult);
	}
}