## This project provides some basic tools for validating and solving Sudoku puzzles.

## Running the program

When running, you must provide arguments. 
args[0] = input directory
args[1] = output directory
args[2...] = names of input files including extensions

The program requires at the very least an input directory. If nothing else is specified, it will attempt to read all files in the input directory, and write the solutions to the same directory. 

Specifying file names, means the program will only read those files from the input directory.

For each file, this program will validate it and attempt to solve it. If there are any errors during processing, an error message will be written to the corresponding file. An entry will also be written to a log file.

## Structure of the Program

The Sudoku class stores the sudoku board as a two dimensional int array.

SudokuSolver exposes methods to solve the sudoku object.
However, if the sudoku is invalid, or not solvable it will throw a SudokuException.
Invalid conditions include sudokus that are not square (unequal row and column length), sudokus whose square root of the width/length is not an integer, and sudokus with repeating values in any row, column, or subgrid.

SudokuIOUtil exposes methods for reading/writing files. It expects the sudoku rows to be Strings matching [0-9xX] with blank sudoku cells represented by either an X or a 0. 
SudokuParser will throw a SudokuParserException with a message if there are any issues opening 
or reading the file. It will also throw a SudokuParserException if the sudoku contains invalid characters
or if the length of the sudoku rows do not match the length of the columns.

SudokuUtil exposes methods for viewing aspects of the puzzle and methods for printing the puzzle in sysout

SudokuValidator provides the puzzle validations for SudokuSolver 

The test.java package contains a main method for testing the project. It will parse all files located in test.resources into Sudoku objects and feed them into the SudokuSolver
