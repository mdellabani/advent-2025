package com.advent.problems;

import com.advent.utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class Day1 {

	private Day1() {
	}

	public static void main(String[] args) throws IOException {
		final List<Integer> combs = FileUtils.readAndParseResource("day1.txt", Day1::parse);

		System.out.println(countPointingZero(combs));
		System.out.println(countAllZeros(combs));
	}

	private static int countPointingZero(final List<Integer> combs) {
		int current = 50;
		int pass = 0;
		for (Integer comb : combs) {
			current = (current + comb) % 100;
			if (current < 0) {
				current += 100;
			} else if (current == 0) {
				pass++;
			}
		}
		return pass;
	}

	private static int countAllZeros(List<Integer> combs) {
		int current = 50;
		int pass = 0;
		for (Integer comb : combs) {
			current += comb;
			if (current > 0) {
				pass += current / 100;
			} else if (current < 0) {
				pass += (-current - 1) / 100 + 1;
			}
			current = (current % 100 + 100) % 100;

		}
		return pass;
	}

	private static int parse(String code) {
		if (code == null || code.isBlank()) {
			throw new IllegalArgumentException("Code is null/blank");
		}
		int sign = 0;
		if (code.charAt(0) == 'R') {
			sign = 1;
		} else if (code.charAt(0) == 'L') {
			sign = -1;
		}
		if (sign == 0) {
			throw new IllegalArgumentException("Illegal code: " + code);
		}
		return sign * Integer.parseInt(code, 1, code.length(), 10);
	}
}
