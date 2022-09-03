package com.github.benjaminjohnston.sudokusolver;

public class SudokuException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SudokuException(String errorMessage) {
        super(errorMessage);
    }
}
