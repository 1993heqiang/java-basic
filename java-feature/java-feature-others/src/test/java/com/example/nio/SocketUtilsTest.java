package com.example.nio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.Socket;
import org.junit.jupiter.api.Test;

/**
 * SocketUtilsTest
 *
 * @author HeQiang
 * @since 2022/11/18
 **/
class SocketUtilsTest {
	@Test
	void smokeTest() {
		Socket socket = new Socket();
		SocketUtils.close(socket);
		assertTrue(socket.isClosed());
	}
}