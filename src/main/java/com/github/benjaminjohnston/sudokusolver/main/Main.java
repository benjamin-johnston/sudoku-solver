package com.github.benjaminjohnston.sudokusolver.main;

import java.io.File;
import java.util.List;

import com.github.benjaminjohnston.sudokusolver.Sudoku;
import com.github.benjaminjohnston.sudokusolver.SudokuException;
import com.github.benjaminjohnston.sudokusolver.SudokuIOUtil;
import com.github.benjaminjohnston.sudokusolver.SudokuSolver;
import com.github.benjaminjohnston.sudokusolver.SudokuUtil;

public class Main {		
	private static String inputDirectoryPath;
	private static String outputDirectoryPath;
	private static String inputFiles;
	
    public static void main(String[] args) { 
    	loadProperties();
    	if (inputDirectoryPath.trim().isEmpty()) {
			System.out.println("Please configure inputDirectory in application.properties before running.");
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
    		solveSudoku(fileName);
    	}
    }
    
    public static void solveAllFiles() { 
    	File[] files = new File(inputDirectoryPath).listFiles();
    	
    	for (File file: files) {
    		solveSudoku(file.getName());
    	}
    }
    
    public static void solveSudoku(String fileName) {
    	if (fileName.endsWith(".txt") && !fileName.endsWith(".sln.txt")) {
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
    
    public static void loadProperties() {
    	inputDirectoryPath = notNull(ApplicationProperties.getInstance().getProperty("inputDirectory"));
    	outputDirectoryPath = notNull(ApplicationProperties.getInstance().getProperty("outputDirectory"));
    	inputFiles = notNull(ApplicationProperties.getInstance().getProperty("inputFiles"));
    }
    
    public static String notNull(String s) {
    	if (s == null)
    		return "";
    	
    	return s.trim();
    }
}
