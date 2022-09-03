package com.github.benjaminjohnston.sudokusolver;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Sudoku {
	private int size = 9;
	
	private int[][] board = new int[size][size];
	
	public Sudoku() {
		
	}
		
	public Sudoku(int boardSize) {
		this.board = new int[boardSize][boardSize];
	}
	
	public Sudoku(int[][] board) {
		this.size = board.length;
		this.board = board;
	}
	
	public int getSize() {
		return size;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	@Override
	public String toString() {
		return "Sudoku [size=" + size + ", board=["
				+ Arrays.stream(board).map(i -> Arrays.toString(i)).collect(Collectors.joining(",")) + "]";
	}	
}
