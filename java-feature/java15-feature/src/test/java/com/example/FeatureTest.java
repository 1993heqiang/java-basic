package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.OutputCaptureExtension;

/**
 * FeatureTest
 *
 * @author HeQiang
 * @since 2022/11/20
 **/
@ExtendWith(OutputCaptureExtension.class)
class FeatureTest {

	@Test
	void stringTest() {
		CharSequence str = "";
		assertTrue(str.isEmpty());

		String block = """
				123
				456
				""";
		System.out.println(block);
	}

	@Test
	void treeMapTest() {
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("1", "hello");
		String old = treeMap.putIfAbsent("1", "world");
		assertEquals("hello", old);
		old = treeMap.putIfAbsent("2", "world");
		assertNull(old);

		String curValue = treeMap.computeIfAbsent("3", s -> s + "...");
		assertEquals("3...", curValue);
		curValue = treeMap.computeIfAbsent("3", s -> s + "!!!");
		assertEquals("3...", curValue);

		curValue = treeMap.computeIfPresent("3", (s, s2) -> s2.repeat(Integer.parseInt(s)));
		assertEquals("3...3...3...", curValue);

		curValue = treeMap.compute("3", (s, s2) -> {
			if (s2 == null) {
				return null;
			}
			return s2.repeat(Integer.parseInt(s));
		});
		assertEquals("3...3...3...3...3...3...3...3...3...", curValue);

		curValue = treeMap.merge("4", "four", (s, s2) -> s + s2);
		assertEquals("four", curValue);
		curValue = treeMap.merge("4", "four", (s, s2) -> s + s2);
		assertEquals("fourfour", curValue);

	}

	/**
	 * 新签名算法
	 */
	@Test
	void edDSATest() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
		KeyPair kp = kpg.generateKeyPair();

		byte[] msg = "hello".getBytes(StandardCharsets.UTF_8);

		Signature sig = Signature.getInstance("Ed25519");
		sig.initSign(kp.getPrivate());
		sig.update(msg);
		byte[] s = sig.sign();

		String encodedString = Base64.getEncoder().encodeToString(s);
		System.out.println(encodedString);

		Signature sig1 = Signature.getInstance("Ed25519");
		sig1.initVerify(kp.getPublic());
		sig1.update(msg);
		boolean verify = sig1.verify(s);
		assertTrue(verify);
	}

}