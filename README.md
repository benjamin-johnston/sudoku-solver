## Sudoku Solver

## Overview

This project provides some basic tools for validating and solving Sudoku puzzles.
The Main class is the entry point of the program. This class will use the application.properties file to 
determine where the sudoku puzzles should be read from, and where the solutions should be written to.
The program reads a sudoku puzzle from an input file and attempts to solve it. If it is unsolvable or invalid 
a message will be written to the output file. Otherwise, it will write the solved puzzle to that file.
The program expects input files to have .txt extension, and solution files will have the same name, but a .sln.txt extension. The program can currently solve 4x4, and 9x9 puzzles. Anything else is currently out of scope.

## Configuration

Input directories, output directories, and specific files can be specified in main/resources/application.properties.

At a minimum, inputDirectory is required.

If you don't specify an output directory, then the input directory will be used as output directory.

If you don't specify any specific files, then all files in the input directory will be considered input.

inputFiles should be a comma delimited list.

Ex:
inputDirectory=src/test/resources/
inputFiles=puzzle1-Gavant.txt,puzzle2-Gavant.txt
outputDirectory=src/test/resources/

## Program Structure

The Main class contains the main controller logic for the program.

The ApplicationProperties is a singleton utility class for loading the application.properties file.

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
