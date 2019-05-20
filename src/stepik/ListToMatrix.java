package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListToMatrix {
    public static void main(String[] args) {
        
        List<String> inputList = getGraphInput();
        String[][] matrix = buildMatrix(inputList);
        int numberOfNodes = matrix.length;
        fillMatrixFromList(inputList, matrix);
        printMatrix(matrix, numberOfNodes);
        
    }
    
    private static List<String> getGraphInput() {
        List<String> listOfLines = new ArrayList<>();
        try(InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in)) {
            String inputLine;
            while(!(inputLine = br.readLine()).equals("")) {
                listOfLines.add(inputLine);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }
    
    private static String[][] buildMatrix(List<String> inputList){
        int size = inputList.size();
        String[][] matrix = new String[size][size];
        for(int i = 0 ; i< matrix.length; i++){
            for(int j = 0; j < matrix.length ; j++) {
                matrix[i][j] = "0";
            }
        }
        return matrix;
    }

    private static void fillMatrixFromList (List<String> inputList, String[][] matrix){
        int counter = 0;
        for(String line : inputList){
            String[] tempArray = line.split(" ");
            for (int i = 1; i < tempArray.length ;i++){
                int position = Integer.parseInt(tempArray[i]);
                matrix[counter][position -1] = "1";
            }
            counter++;
        }
    }
    
    private static void printMatrix(String[][] matrixToPrint, int numberOfNodes) {
        int index = 0;
        for (String[] lines : matrixToPrint){
            for (String s : lines){
                System.out.printf(s);
                if (index != numberOfNodes -1){
                    System.out.printf(" ");
                }
                index++;
            }
            System.out.println();
            index =0;
        }
    }
}
