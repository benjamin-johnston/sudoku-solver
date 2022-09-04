package com.github.benjaminjohnston.sudokusolver;

import java.util.ArrayList;

public class SudokuSolver {
	private ArrayList<String> validationErrors = new ArrayList<String>();

	public void solveSudoku(Sudoku sudoku) throws SudokuException {
		validationErrors = SudokuValidator.validateBoard(sudoku);

		if (validationErrors.size() == 0 && !solveBoard(sudoku)) {
			throw new SudokuException("Board not solvable.");
		}

	}

	private boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}

		return false;
	}

	private boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}

		return false;
	}

	private boolean isNumberInLocalBox(int[][] board, int number, int row, int column) {
		int localGridSize = (int) Math.sqrt(board.length);
		int localBoxRowStart = row - row % localGridSize;
		int localBoxColumnStart = column - column % localGridSize;

		for (int i = localBoxRowStart; i < localBoxRowStart + localGridSize; i++) {
			for (int j = localBoxColumnStart; j < localBoxColumnStart + localGridSize; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isValidPlacement(int[][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column)
				&& !isNumberInLocalBox(board, number, row, column);
	}

	// use recursive brute force back tracking algorithm to solve board
	private boolean solveBoard(Sudoku sudoku) throws SudokuException {
		int[][] board = sudoku.getBoard();
		int size = sudoku.getSize();

		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				if (board[row][column] == 0) {
					for (int number = 1; number <= size; number++) {
						if (isValidPlacement(board, number, row, column)) {
							board[row][column] = number;

							// if board is solvable from this point we can keep this number placement and
							// continue
							if (solveBoard(sudoku)) {
								return true;
							} else { // if not clear it out, and try next number
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}

		return true;
	}

	public ArrayList<String> getValidationErrors() {
		return validationErrors;
	}
}
