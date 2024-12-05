package kram.advent.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFromFile {

    private static final ClassLoader cl = ReadFromFile.class.getClassLoader();

    public static String readFileString(String file) {
        try {
            URI uri = cl.getResource(file).toURI();
            return Files.readString(Path.of(uri), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFileList(String file) {
        try {
            URI uri = cl.getResource(file).toURI();
            return Files.readAllLines(Path.of(uri), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
