package com.github.benjaminjohnston.sudokusolver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuIOUtil {   
    public static List<String> readFromFile(String fileName) throws IOException {
    	List<String> lines = new ArrayList<String>();
    	
        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        	
        String line;
        while ((line = br.readLine()) != null) {
            if(!line.isEmpty()){
                lines.add(line.trim().replaceAll("[Xx]", "0"));
            } else {
                break;
            }
        }
        
        br.close();

        return lines;
    }
    
    public static void writeToFile(String outputDirectoryPath, String fileName, Sudoku sudoku) throws IOException {  	
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectoryPath + "//" + fileName));
        
        writer.write(SudokuIOUtil.formatForWrite(sudoku));
        writer.close();   	
    }
    
    public static void writeErrorsToFile(String outputDirectoryPath, String fileName, String data) {   	
        BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(outputDirectoryPath + "//" + fileName));
			
	        writer.write(data);
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public static String formatForWrite(Sudoku sudoku) {
    	String str = "";
    	
    	List<String> list = new ArrayList<String>();
    	
		for (int i = 0; i < sudoku.getSize(); i++) {
			str = "";
			for (int j = 0; j < sudoku.getSize(); j++) {
				str += sudoku.getBoard()[i][j];
			}
			list.add(str);
		}
		
		return list.stream().collect(Collectors.joining("\n"));
	}
}
