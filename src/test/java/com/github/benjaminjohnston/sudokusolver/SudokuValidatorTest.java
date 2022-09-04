package com.github.benjaminjohnston.sudokusolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class SudokuValidatorTest {
	private static String inputDirectoryPath = "src/test/resources/sudokuValidator/";

	@Test
	void invalidRowTest() {
		try {
			List lines = SudokuIOUtil.readFromFile(inputDirectoryPath + "invalidRow.txt");
			Sudoku sudoku = SudokuUtil.buildSudoku(lines);
			SudokuSolver solver = new SudokuSolver();
			solver.solveSudoku(sudoku);
			assertEquals("Row 0 contains duplicates.", solver.getValidationErrors().get(0));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void invalidColumnTest() {		
		try {
			List lines = SudokuIOUtil.readFromFile(inputDirectoryPath + "invalidColumn.txt");
			Sudoku sudoku = SudokuUtil.buildSudoku(lines);
			SudokuSolver solver = new SudokuSolver();
			solver.solveSudoku(sudoku);
			assertEquals("Column 8 contains duplicates.", solver.getValidationErrors().get(0));
		} catch (Exception e) {
			fail(e);
		}		
	}

	@Test
	void invalidlocalGridTest() {
		try {
			List lines = SudokuIOUtil.readFromFile(inputDirectoryPath + "invalidLocalGrid.txt");
			Sudoku sudoku = SudokuUtil.buildSudoku(lines);
			SudokuSolver solver = new SudokuSolver();
			solver.solveSudoku(sudoku);
			assertEquals("Local Grid starting at upper left position (0,6) contains duplicates.", solver.getValidationErrors().get(0));
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void invalidGridTest() {		
		try {
			List lines = SudokuIOUtil.readFromFile(inputDirectoryPath + "4x4grid.txt");
			Sudoku sudoku = SudokuUtil.buildSudoku(lines);
			SudokuSolver solver = new SudokuSolver();
			solver.solveSudoku(sudoku);
			assertEquals("Invalid grid. Grid should be 9x9.", solver.getValidationErrors().get(0));
		} catch (Exception e) {
			fail(e);
		}
	}
}
