package test.java;

import java.io.File;
import java.util.List;

import main.java.ApplicationProperties;
import main.java.Sudoku;
import main.java.SudokuException;
import main.java.SudokuIOUtil;
import main.java.SudokuSolver;
import main.java.SudokuUtil;

public class Test {	
	private static String inputDirectoryPath;
	private static String outputDirectoryPath;
	private static String inputFiles;
	
    public static void main(String[] args) { 
    	loadProperties();
    	if (inputDirectoryPath.trim().isEmpty()) {
			System.out.println("Please configure application.properties before running.");
    	} else if (outputDirectoryPath.trim().isEmpty()) {
    		outputDirectoryPath = inputDirectoryPath;
    	}
    	
    	if (inputFiles.trim().isEmpty()) {
    		solveAllFiles();
    	} else {
    		solveSpecificFiles();
    	}
    }
    
    public static void solveSpecificFiles() {
    	String[] files = inputFiles.split(",");
    	
    	for (String fileName : files) {
    		if (!fileName.trim().isEmpty()) {
		    	try {
		    		List<String> lines = SudokuIOUtil.readFromFile(inputDirectoryPath + "//" + fileName);
		    		Sudoku sudoku = SudokuUtil.buildSudoku(lines);
		    		SudokuSolver solver = new SudokuSolver();
		    		solver.solveSudoku(sudoku);
		    		
		    		SudokuIOUtil.writeToFile(outputDirectoryPath, fileName.replace(".txt", ".sln.txt"), sudoku);
				} catch (SudokuException e) {
					SudokuIOUtil.writeErrorsToFile(outputDirectoryPath, fileName.replace(".txt", ".sln.txt"), 
							"Sudoku Error: " + e.getMessage());
				} catch (Exception e) {
					SudokuIOUtil.writeErrorsToFile(outputDirectoryPath, fileName.replace(".txt", ".sln.txt"), 
							e.getMessage());
				}
    		}
    	}
    }
    
    public static void solveAllFiles() { 
    	File[] files = new File(inputDirectoryPath).listFiles();
    	
    	for (File file: files) {
	    	try {
	    		System.out.println("Testing " + file.getName() + ":");
	    		List<String> lines = SudokuIOUtil.readFromFile(inputDirectoryPath 
	    				+ file.getName());
	    		Sudoku sudoku = SudokuUtil.buildSudoku(lines);
	    		SudokuUtil.print(sudoku);
	    		SudokuSolver solver = new SudokuSolver();
	    		solver.solveSudoku(sudoku);
				System.out.println("Solved:");
				SudokuUtil.print(sudoku);
				SudokuIOUtil.writeToFile(outputDirectoryPath, file.getName().replace(".txt", ".sln.txt"), sudoku);
			} catch (SudokuException e) {
				SudokuIOUtil.writeErrorsToFile(outputDirectoryPath, file.getName().replace(".txt", ".sln.txt"), 
						"Sudoku Error: " + e.getMessage());
			} catch (Exception e) {
				SudokuIOUtil.writeErrorsToFile(outputDirectoryPath, file.getName().replace(".txt", ".sln.txt"), 
						e.getMessage());
			}
    	}
    }
    
    public static void loadProperties() {
    	inputDirectoryPath = ApplicationProperties.getInstance().getProperty("inputDirectory");
    	outputDirectoryPath = ApplicationProperties.getInstance().getProperty("outputDirectory");
    	inputFiles = ApplicationProperties.getInstance().getProperty("inputFiles");
    }
}
