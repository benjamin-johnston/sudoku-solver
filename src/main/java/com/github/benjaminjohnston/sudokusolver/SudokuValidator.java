package com.github.benjaminjohnston.sudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuValidator {
    private static void validate9x9Grid(Sudoku sudoku, ArrayList<String> errors) {   	
    	if (sudoku.getBoard().length != 9 || sudoku.getBoard()[0].length != 9) {
    		errors.add("Invalid grid. Grid should be 9x9.");
    	}
    } 

       
	private static void validateRows(Sudoku sudoku, ArrayList<String> errors) {	
		for (int i = 0; i < sudoku.getSize(); i++) {
			if (hasDuplicates(SudokuUtil.getRow(sudoku, i))) {
				errors.add("Row " + i + " contains duplicates.");
			}
		}
	}
	
	private static void validateColumns(Sudoku sudoku, ArrayList<String> errors) {
		for (int j = 0; j < sudoku.getSize(); j++) {
			if (hasDuplicates(SudokuUtil.getColumn(sudoku, j))) {
				errors.add("Column " + j + " contains duplicates.");
			}
		}
	}
	
	private static void validateLocalGrids(Sudoku sudoku, ArrayList<String> errors) {	
		int localGridSize = (int) Math.sqrt(sudoku.getSize());
		
		for (int i = 0; i < sudoku.getSize(); i+=localGridSize) {
			for (int j = 0; j < sudoku.getSize(); j+=localGridSize) {
				if (hasDuplicates(SudokuUtil.getLocalGrid(sudoku, i, j))) {
					errors.add("Local Grid starting at upper left position (" + i + "," + j + ") " +
							"contains duplicates.");
				}
			}
		}
	}
	
	public static ArrayList<String> validateBoard(Sudoku sudoku) throws SudokuException {
		ArrayList<String> errors = new ArrayList<String>();
		
		validate9x9Grid(sudoku, errors);
		validateRows(sudoku, errors);
		validateColumns(sudoku, errors); 
		validateLocalGrids(sudoku, errors);
		
		return errors;
	}
	
	private static boolean hasDuplicates(int[] arr) { 			
	    Set<Integer> set = new HashSet<>();
	         
	    for (Integer i : Arrays.stream(arr).boxed().collect(Collectors.toList())) {
	        if (i != 0 && !set.add(i)) {
	        	return true;
	        }
	    }
	    return false;
	}
}


