package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddEdgeToGraph {
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        int numberOfNodes = countVertices(list);
        boolean flag = isInputCorrect(list, numberOfNodes);
        int[] edge = buildEdge(list);
        String[][] matrix = buildMatrix(list);
        String[][] matrixToPrint = (addEdgeToMatrix(matrix, edge));
        if ( flag ){
            printMatrix(matrixToPrint, numberOfNodes);
        }else {
            System.out.println("BŁĄD");
        }
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
    
    private static int[] buildEdge(List<String> list){
        int[] edge = new int[2];
        String[] buffer = list
            .remove(list.size()-1)
            .split(" ");
        for (int i =0; i < edge.length ; i++){
            edge[i] = Integer.parseInt(buffer[i]);
        }
        return edge;
    }
    private static int countVertices(List<String> list){
        return list.size() -1;
    }
    
    private static boolean isInputCorrect( List<String> list, int numberOfNodes){
        String lastRowOfList = "";
        String[] buffer = list
            .remove(list.size()-1)
            .split(" ");
        if ( buffer.length != 2) {
            return false;
        }else {
            for( String s : buffer) {
                int num = Integer.parseInt(s);
                if (num < 1 || num > numberOfNodes) {
                    return false;
                }
                lastRowOfList += s + " ";
            }
        }
        list.add(lastRowOfList.trim());
        return true;
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
    
    private static String[][] addEdgeToMatrix(String[][] matrix, int[] edge){
        int firstNode = edge[0];
        int secondNode = edge[1];
        matrix[firstNode - 1][secondNode - 1] = "1";
        matrix[secondNode - 1][firstNode - 1] = "1";
        return matrix;
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
