package test.java;

import java.io.File;
import java.util.List;

import main.java.Sudoku;
import main.java.SudokuException;
import main.java.SudokuIOUtil;
import main.java.SudokuSolver;
import main.java.SudokuUtil;

public class Test {
	private static String TEST_RESOURCE_PATH = "src//test//resources//";
	
    public static void main(String[] args) { 
    	File[] files = new File(TEST_RESOURCE_PATH).listFiles();
    	
    	for (File file: files) {
	    	try {
	    		System.out.println("Testing " + file.getName() + ":");
	    		List<String> lines = SudokuIOUtil.readFromFile(TEST_RESOURCE_PATH 
	    				+ file.getName());
	    		Sudoku sudoku = SudokuUtil.buildSudoku(lines);
	    		SudokuUtil.print(sudoku);
	    		SudokuSolver solver = new SudokuSolver();
	    		solver.solveSudoku(sudoku);
				System.out.println("Solved:");
				SudokuUtil.print(sudoku);
			} catch (SudokuException e) {
				System.out.println("Sudoku Error: " + e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
    	}
    }
}
