package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GraphSquare {
    public static void main(String[] args) {
        List<String> inputList = getGraphInput();
        String[][] firstMatrix = buildMatrix(inputList);
        String[][] diagonalMatrix = buildMatrix(inputList);
        fillMatrixFromList(inputList, firstMatrix);
        fillDiagonalWithZero(diagonalMatrix);
        copyDiagFromMatrixToDiagonal(firstMatrix, diagonalMatrix);
        
        String[][] squaredMatrix = squareMatrix(firstMatrix);
        String[][] squaredGraphMatrix = squaredGraphMatrix(firstMatrix, squaredMatrix, diagonalMatrix);
        List<String> matrixList = transformMatrixToList(squaredGraphMatrix);
        List<String> resultList = transformMatrixListToList(matrixList);
        printList(resultList);
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
    
    private static String[][] squareMatrix(String[][] firstMatrix){
        int size = firstMatrix.length;
        String[][] returnMatrix = new String[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int sum = 0;
                for(int k = 0; k < size; k++) {
                    sum += (Integer.parseInt(firstMatrix[i][k]) * Integer.parseInt(firstMatrix[k][j]));
                }
                returnMatrix[i][j] = sum +"";
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(returnMatrix[i][j].equals("0") || returnMatrix[i][j].equals("1"))
                    continue;
                else
                    returnMatrix[i][j] = "1";
            }
        }
        return returnMatrix;
    }
    
    private static String[][] squaredGraphMatrix(String[][] firstMatrix, String[][] squaredMatrix, String[][] diagonalMatrix){
        int size = firstMatrix.length;
        String[][] returnMatrix = new String[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                returnMatrix[i][j] = (Integer.parseInt(firstMatrix[i][j]) + Integer.parseInt(squaredMatrix[i][j])+"");
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                returnMatrix[i][j] = (Integer.parseInt(returnMatrix[i][j]) - Integer.parseInt(diagonalMatrix[i][j])+"");
            }
        }
//        zeroingDiagonalMatrix(returnMatrix);
        return returnMatrix;
    }
    
    private static void fillDiagonalWithZero(String[][] matrix){
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = "0";
            }
        }
    }
    
    private static void copyDiagFromMatrixToDiagonal(String[][] matrix, String[][] diagonalMatrix){
        int counter = 0;
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][counter].equals("0"))
                diagonalMatrix[i][counter] = "1";
            else
                diagonalMatrix[i][counter] = "0";
            counter++;
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
