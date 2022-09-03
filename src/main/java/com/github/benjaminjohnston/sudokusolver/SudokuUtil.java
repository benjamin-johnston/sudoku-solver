package com.github.benjaminjohnston.sudokusolver;

import java.util.List;

public class SudokuUtil {
	public static int getValue(Sudoku sudoku, int row, int column){
	    return sudoku.getBoard()[row][column];
	}	

	public static int[] getRow(Sudoku sudoku, int index){
	    return sudoku.getBoard()[index];
	}		
	
	public static int[] getColumn(Sudoku sudoku, int index) {
	    int[] column = new int[sudoku.getBoard()[0].length]; 
	    for(int i = 0; i < column.length; i++){
	       column[i] = sudoku.getBoard()[i][index];
	    }
	    return column;
	}
	
	public static int[] getLocalGrid(Sudoku sudoku, int row, int column) {
		int localGridSize = (int) Math.sqrt(sudoku.getSize());
		int localBoxRowStart = row - row % localGridSize;
		int localBoxColumnStart = column - column % localGridSize;
		
		int[] localGrid = new int[sudoku.getSize()];
		int index = 0;
		
		for (int i = localBoxRowStart; i < localBoxRowStart + localGridSize; i++) {
			for (int j = localBoxColumnStart; j < localBoxColumnStart + localGridSize; j++) {
				localGrid[index] = sudoku.getBoard()[i][j];
				index++;
			}
		}

		return localGrid;
	}
	
	public static void print(Sudoku sudoku) {
		for (int i = 0; i < sudoku.getSize(); i++) {
			for (int j = 0; j < sudoku.getSize(); j++) {
				System.out.print(sudoku.getBoard()[i][j]);
			}
			
			System.out.println();
		}
	}
	
	public static void printPretty(Sudoku sudoku) {
		int localGridSize = (int) Math.sqrt(sudoku.getSize());
		
		String lineSeparator = "";
		
		for (int i = 0; i < sudoku.getSize() + localGridSize - 1; i++) {
			lineSeparator += "-";
		}		
		
		for (int row = 0; row < sudoku.getSize(); row++) {
			if (row % localGridSize == 0 && row!=0) {
				System.out.println(lineSeparator);
			} 	
			for (int col = 0; col < sudoku.getSize(); col++) {
				if (col % localGridSize == 0 && col!=0) {
					System.out.print("|");
				} 
				System.out.print(sudoku.getBoard()[row][col]);
			}
			
			System.out.println();
		}
		
	}	
	
    public static Sudoku buildSudoku(List<String> lines) throws SudokuException {
        for (String l : lines) {
        	if (l.length() != lines.size()) {
        		throw new SudokuException("Sudoku length does not equal sudoku width.");
        	}
        	if (!l.matches("[0-9Xx]+")) {
        		throw new SudokuException("Sudoku contains invalid characters.");
        	}
        }
        
        int[][] board = new int[lines.size()][lines.size()];
        
        for (int i = 0; i < lines.size(); i++) {
        	for (int j = 0; j < lines.size(); j++) {
        		board[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
        	}
        }
        
        return new Sudoku(board);
    }
}
