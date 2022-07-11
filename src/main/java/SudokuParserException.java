package main.java;

public class SudokuParserException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SudokuParserException(String errorMessage) {
        super(errorMessage);
    }
}
