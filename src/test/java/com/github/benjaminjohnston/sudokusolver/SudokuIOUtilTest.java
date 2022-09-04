package com.github.benjaminjohnston.sudokusolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SudokuIOUtilTest {

	@Test
	void blankFileTest() {
		try {
			List<String> lines = SudokuIOUtil.readFromFile("src/test/resources/sudokuIOUtil/blankfile.txt");
			assertEquals(new ArrayList<String>(), lines);
		} catch (IOException e) {
			fail(e);
		}
	}
	
	@Test
	void oneLineTest() {
		try {
			List<String> lines = SudokuIOUtil.readFromFile("src/test/resources/sudokuIOUtil/oneline.txt");
			assertEquals(new ArrayList<String>(Arrays.asList("hello")), lines);
		} catch (IOException e) {
			fail(e);
		}
	}
	
	@Test
	void sudokuTest() {
		try {
			List<String> lines = SudokuIOUtil.readFromFile("src/test/resources/sudokuIOUtil/sudoku.txt");
			assertEquals(new ArrayList<String>(
					Arrays.asList("000150070","106000820","300860040","900400567",
							"004708300","732006004","040081009","017000208","050037000")), lines);
		} catch (IOException e) {
			fail(e);
		}
	}
	
	

}
