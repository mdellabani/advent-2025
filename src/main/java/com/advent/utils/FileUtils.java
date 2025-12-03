package com.advent.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileUtils {

	private FileUtils() {}

	public static <T> List<T> readAndParseResource(String filename, Function<String, T> parser) throws IOException {
		final List<T> results = new ArrayList<>();
		final InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filename);
		if (inputStream == null) {
			throw new IOException("Resource file not found: " + filename);
		}
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				T parsed = parser.apply(line);
				results.add(parsed);
			}
		}

		return results;
	}

}
