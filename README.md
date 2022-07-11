## Sudoku Solver

## Overview

This project provides some basic tools for validating and solving Sudoku puzzles.
Main is the entry point to the application. Main will use the application.properties file to 
determine where to read the sudoku puzzles from, and where to write the solutions.
This application reads a sudoku puzzle from an input file and attempts to solve it. If it is unsolvable or invalid 
a corresponding message is written to the output file. Otherwise, it writes the solved puzzle to that file.
Input files are expected to have a .txt extension. Solution files will be written with the same name, but with a .sln.txt extension. 

This application can currently solve 4x4, and 9x9 puzzles. Anything else is currently out of scope.

## Configuration

Input directories, output directories, and specific files can be specified in main/resources/application.properties.

At a minimum, inputDirectory is required.

If you don't specify an output directory, then the input directory will be used as the output directory.

If you don't specify any specific files, then all files in the input directory will be considered input.

inputFiles should be a comma delimited list.

Ex: <br />
inputDirectory=src/test/resources/ <br />
inputFiles=puzzle1-Gavant.txt,puzzle2-Gavant.txt <br />
outputDirectory=src/test/resources/ <br />

## Structure

The Main class contains the main controller logic for the program.

The ApplicationProperties is a singleton utility class for loading the application.properties file.

The Sudoku class stores the sudoku board as a two dimensional int array.

SudokuSolver exposes methods to solve the sudoku object.
However, if the sudoku is invalid, or not solvable it will throw a SudokuException.
Invalid conditions include sudokus that are not square (unequal row and column length), sudokus whose square root of the width/length is not an integer, and sudokus with repeating values in any row, column, or subgrid.

SudokuIOUtil exposes methods for reading/writing files. It expects the sudoku rows to be Strings matching [0-9xX] with blank sudoku cells represented by either an X or a 0. 
SudokuParser will throw a SudokuParserException with a message if there are any issues opening or reading the file. It will also throw a SudokuParserException if the sudoku contains invalid characters or if the length of the sudoku rows do not match the length of the columns.

SudokuUtil exposes methods for viewing aspects of the puzzle and methods for printing the puzzle in sysout.

SudokuValidator provides the puzzle validations for SudokuSolver.
