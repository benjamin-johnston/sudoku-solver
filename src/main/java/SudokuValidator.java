package main.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuValidator {
	
    private static void validGridSize(Sudoku sudoku) throws SudokuException {     	
    	double squareRoot = Math.sqrt(sudoku.getSize()); 

    	if ((squareRoot - Math.floor(squareRoot)) != 0) 
    		throw new SudokuException("Sudoku board size is not a perfect square.");
    	
    	if (sudoku.getSize() > 9) 
    		throw new SudokuException("Sudoku solver is not ready for grids sizes > 9. Check back soon.");
    } 
    
    private static void isSquareGrid(Sudoku sudoku) throws SudokuException { 
    	if (sudoku.getBoard().length != sudoku.getBoard()[0].length) {
    		throw new SudokuException("Sudoku length does not equal sudoku width.");
    	}
    } 
       
	private static void rowsValid(Sudoku sudoku) throws SudokuException {	    
		for (int i = 0; i < sudoku.getSize(); i++) {
			if (hasDuplicates(SudokuUtil.getRow(sudoku, i))) {
				throw new SudokuException("Row " + i + " contains duplicates.");
			}
		}
	}
	
	private static void columnsValid(Sudoku sudoku) throws SudokuException {	    
		for (int j = 0; j < sudoku.getSize(); j++) {
			if (hasDuplicates(SudokuUtil.getColumn(sudoku, j))) {
				throw new SudokuException("Column " + j + " contains duplicates.");
			}
		}
	}
	
	private static void localGridsValid(Sudoku sudoku) throws SudokuException {
		int localGridSize = (int) Math.sqrt(sudoku.getSize());
		
		for (int i = 0; i < sudoku.getSize(); i+=localGridSize) {
			for (int j = 0; j < sudoku.getSize(); j+=localGridSize) {
				if (hasDuplicates(SudokuUtil.getLocalGrid(sudoku, i, j))) {
					throw new SudokuException("Local Grid starting at upper left position (" + i + "," + j + ") " +
							"contains duplicates.");
				}
			}
		}
	}
	
	public static void isBoardValid(Sudoku sudoku) throws SudokuException {	
		validGridSize(sudoku);
		isSquareGrid(sudoku);
		rowsValid(sudoku);
		columnsValid(sudoku); 
		localGridsValid(sudoku);
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


