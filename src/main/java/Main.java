package main.java;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {		
    public static void main(String[] args) { 
    	if (args.length == 0) {
    		System.out.println("Must provide input/output directories and filenames as arguments");
    	} else if (args.length == 1) {
    		solveAllSudokusInDirectory(args[0], args[0]);
    	} else if (args.length == 2) {
    		solveAllSudokusInDirectory(args[0], args[1]);
    	} else if (args.length > 2) {
    		String[] fileNames = new String[args.length - 2];
    		
    		for (int i = 0; i < args.length - 2; i++) {
    			fileNames[i] = args[i + 2];
    		}
    		
    		solveSudokusInDirectory(args[0], args[1], fileNames);
    	}
    }
    
    public static void solveAllSudokusInDirectory(String inputDirectory, String outputDirectory) {
    	File[] files = new File(inputDirectory).listFiles();
    	
    	for (File file: files) {
	    	try {
	    		List<String> lines = SudokuIOUtil.readFromFile(inputDirectory + "//" + file.getName());
	    		Sudoku sudoku = SudokuUtil.buildSudoku(lines);
	    		SudokuSolver solver = new SudokuSolver();
	    		solver.solveSudoku(sudoku);
	    		
				SudokuIOUtil.writeToFile(outputDirectory, file.getName().replace(".txt", ".sln.txt"), sudoku);
			} catch (SudokuException e) {
				SudokuIOUtil.writeErrorsToFile(outputDirectory, file.getName().replace(".txt", ".sln.txt"), 
						"Sudoku Error: " + e.getMessage());
			} catch (Exception e) {
				SudokuIOUtil.writeErrorsToFile(outputDirectory, file.getName().replace(".txt", ".sln.txt"), 
						e.getMessage());
			}
    	}
    }
    
    public static void solveSudokusInDirectory(String inputDirectory, String outputDirectory, String[] fileNames) {      	
    	for (String fileName : fileNames) {
    		if (!fileName.trim().isEmpty()) {
		    	try {
		    		List<String> lines = SudokuIOUtil.readFromFile(inputDirectory + "//" + fileName);
		    		Sudoku sudoku = SudokuUtil.buildSudoku(lines);
		    		SudokuSolver solver = new SudokuSolver();
		    		solver.solveSudoku(sudoku);
		    		
		    		SudokuIOUtil.writeToFile(outputDirectory, fileName.replace(".txt", ".sln.txt"), sudoku);
				} catch (SudokuException e) {
					SudokuIOUtil.writeErrorsToFile(outputDirectory, fileName.replace(".txt", ".sln.txt"), 
							"Sudoku Error: " + e.getMessage());
				} catch (Exception e) {
					SudokuIOUtil.writeErrorsToFile(outputDirectory, fileName.replace(".txt", ".sln.txt"), 
							e.getMessage());
				}
    		}
    	}
    }
}
