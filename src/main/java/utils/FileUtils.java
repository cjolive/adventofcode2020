package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

  public static Stream<String> fileAsStringStream(String filename) {
    try {
      Path path = Paths.get(FileUtils.class.getClassLoader().getResource(filename).toURI());
      return Files.lines(path);
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException("Could not read file", e);
    }
  }
}
