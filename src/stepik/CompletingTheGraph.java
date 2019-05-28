package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompletingTheGraph {
    public static void main(String[] args) {
        List<String> inputList = getGraphInput();
        String[][] matrix = buildMatrix(inputList);
        fillMatrixFromList(inputList, matrix);
        transformMatrixToCompleting(matrix);
        zeroingDiagonalMatrix(matrix);
        List<String> matrixList = transformMatrixToList(matrix);
        List<String> returnList = transformMatrixListToList(matrixList);
        printList(returnList);
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
    
    private static void transformMatrixToCompleting(String[][] matrix){
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j].equals("0"))
                    matrix[i][j] = "1";
                else matrix[i][j] = "0";
            }
        }
    }
    
    private static void zeroingDiagonalMatrix(String[][] matrix){
        int counter = 0;
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][counter] = "0";
            counter++;
        }
    }
    
    private static List<String> transformMatrixToList(String[][] matrix){
        List<String> returnList = new ArrayList<>();
        for(int i = 0; i < matrix.length ; i++) {
            StringBuilder line = new StringBuilder();
            for(int j = 0; j < matrix.length; j++) {
                line.append(matrix[i][j]);
                line.append(" ");
            }
            returnList.add(line.toString().trim());
        }
        return returnList;
    }
    private static List<String> transformMatrixListToList(List<String> matrixList) {
        List<String> returnList = new ArrayList<>();
        int nodeCounter = 1;
        for(String lines : matrixList) {
            StringBuilder line = new StringBuilder();
            line.append(nodeCounter);
            line.append(" ");
            int counter = 1;
            String[] tempArray = lines.split(" ");
            for(String node : tempArray){
                if(node.equals("1")){
                    line.append(counter);
                    line.append(" ");
                }
                counter++;
            }
            nodeCounter++;
            returnList.add(line.toString().trim());
        }
        return returnList;
    }
    
    private static void printList(List<String> returnList){
        for(String line : returnList)
            System.out.println(line);
    }
}
