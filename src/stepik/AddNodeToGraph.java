package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddNodeToGraph {
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        String[] edges = getLastFromList(list);
        String[][] matrix = buildMatrix(list);
        String[][] newMatrix = addNodeToMatrix(matrix);
        addEdgesToMatrix(newMatrix,edges);
        printMatrix(newMatrix, newMatrix.length);
    }
    
    private static List<String> getGraphInput(){
        List<String> listOfLines = new ArrayList<>();
        try(InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in)){
            String inputLine;
            while ( !(inputLine = br.readLine()).equals("") ){
                listOfLines.add(inputLine);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return listOfLines;
    }
    
    private static String[] getLastFromList(List<String> list){
        return list
            .remove(list.size()-1)
            .split(" ");
    }
    
    private static String[][] buildMatrix(List<String> listOfLines){
        int size = listOfLines.size();
        int index = 0;
        String[][] matrix = new String[size][size];
        
        for(String lines : listOfLines){
            matrix[index] = lines.split(" ");
            index++;
        }
        return matrix;
    }
    
    private static String[][] addNodeToMatrix(String[][] matrix){
        String[][] matrixWithNewNode = new String[matrix.length + 1][matrix.length +1];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrixWithNewNode[i][j] = matrix[i][j];
            }
        }
        for(int i = 0; i < matrixWithNewNode.length ; i++) {
            for(int j = 0; j < matrixWithNewNode.length; j++) {
                if (matrixWithNewNode[i][j] == null ){
                    matrixWithNewNode[i][j] = "0";
                }
            }
        }
        return matrixWithNewNode;
    }
    
    private static void addEdgesToMatrix(String[][] matrix, String[] edges){
        int index = 0;
        matrix[matrix.length -1] = edges;
        for( String[] subArray : matrix){
            subArray[subArray.length -1] = edges[index];
            index++;
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
