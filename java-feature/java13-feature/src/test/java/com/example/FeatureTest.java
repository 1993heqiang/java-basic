package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.util.ResourceUtils;

/**
 * FeatureTest
 *
 * @author HeQiang
 * @since 2022/11/20
 **/
@ExtendWith(OutputCaptureExtension.class)
class FeatureTest {

	/**
	 * newFileSystem(Path) <p/> newFileSystem(Path, Map<String, ?>) <p/> newFileSystem(Path,
	 * Map<String, ?>, ClassLoader) <p/>
	 *
	 * @throws IOException
	 */
	@Test
	void fileSystemsTest() throws IOException {
		File helloFile = ResourceUtils.getFile("classpath:static.zip");
		try (FileSystem fileSystem = FileSystems.newFileSystem(helloFile.toPath())) {
			Assertions.assertNotNull(fileSystem);
		}
	}

	@Test
	void watcherServiceTest(CapturedOutput output) throws IOException, InterruptedException {
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = ResourceUtils.getFile("classpath:./").toPath();
		var watchService = fileSystem.newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

		CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread watcherThread = new Thread(() -> {
			try {
				for (; ; ) {
					// retrieve key
					WatchKey key = watchService.take();

					// process events
					for (WatchEvent<?> event : key.pollEvents()) {
						System.out.println(
								"Kind:" + event.kind() + ", Context:" + event.context() + ".");
					}

					// success
					System.out.println("result: success");
					countDownLatch.countDown();

					// reset the key
					boolean valid = key.reset();
					if (!valid) {
						// object no longer registered
						System.err.println("object no longer registered.");
					}
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		});

		watcherThread.setDaemon(true);
		watcherThread.start();

		Path helloFilePath = path.resolve("hello.txt");
		if (!helloFilePath.toFile().exists()) {
			boolean ok = helloFilePath.toFile().createNewFile();
			if (!ok) {
				System.err.println("Failed to create file.");
			}
		}

		Files.writeString(helloFilePath, "new");
		boolean awaitResult = countDownLatch.await(1, TimeUnit.SECONDS);
		if (!awaitResult) {
			System.err.println("error");
		}

		assertThat(output).contains("result: success");
	}
}