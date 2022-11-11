package org.rookie.utils;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CharsetUtils {

  private static final List<Charset> TRANSFER_CHARSET_LIST = Lists.newArrayList(ISO_8859_1, UTF_8,
      Charset.forName("GB2312"), Charset.forName("GBK"));

  public static void main(String[] arg) throws IOException, URISyntaxException {
    URI charsetDir = ClassLoader.getSystemResource("charset").toURI();
    try (Stream<Path> pathStream = Files.walk(Path.of(charsetDir))) {
      pathStream.forEach(path -> {
        if (Files.isRegularFile(path)) {
          String fileName = path.getFileName().toString();
          String[] arr = fileName.split("\\.");
          if (arr.length != 2) {
            System.err.printf("%s fileName is error.", fileName);
            System.err.println();
            return;
          }
          testEncode(path, Charset.forName(arr[0]));
        }
      });
    } catch (UnsupportedCharsetException e) {
      // ignore
    }
  }

  private static void testEncode(Path filePath, Charset fileCharset) {
    try {
      byte[] bytes = Files.readAllBytes(filePath);
      TRANSFER_CHARSET_LIST.forEach(charset -> {
        byte[] transferData = new String(bytes, charset).getBytes(charset);
        System.out.printf("originCharset: %s , transferCharset: %s, data is equals: %s",
            fileCharset == null ? "Unknown" : fileCharset, charset,
            Arrays.equals(bytes, transferData));
        System.out.println();
      });
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    System.out.println("-------------------------------------------");
  }
}
