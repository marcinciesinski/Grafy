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
        boolean isDirected = isDirectedEdge(list);
        boolean isCorrect = isInputCorrect(list, numberOfNodes);
        if ( isCorrect ){
            int[] edge = buildEdge(list);
            String[][] matrix = buildMatrix(list);
            String[][] matrixToPrint = (addEdgeToMatrix(matrix, edge, isDirected));
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
        String[] buffer = getLastFromList(list);
        for (int i =0; i < edge.length ; i++){
            edge[i] = Integer.parseInt(buffer[i]);
        }
        return edge;
    }
    private static int countVertices(List<String> list){
        return list.size() -1;
    }
    
    private static String[] getLastFromList(List<String> list){
        String[] buffer = list
            .remove(list.size()-1)
            .split(" ");
        return buffer;
    }
    
    private static void addLastToList(String[] buffer, List<String> list){
        String lastRowOfList = "";
        for (String s : buffer){
            lastRowOfList += s + " ";
        }
        list.add(lastRowOfList.trim());
    }
    
    private static boolean isDirectedEdge(List<String> list){
        String lastRowOfList = "";
        String[] buffer = getLastFromList(list);
        if ( buffer.length != 3) {
            addLastToList(buffer, list);
            return false;
        }else if ( !buffer[2].equals("T")){
            addLastToList(buffer, list);
            return false;
        }
        for (int i = 0; i < buffer.length - 1 ; i ++){
            lastRowOfList += buffer[i] + " ";
        }
        list.add(lastRowOfList.trim());
        return true;
    }
    
    private static boolean isInputCorrect( List<String> list, int numberOfNodes){
        String lastRowOfList = "";
        String[] buffer = getLastFromList(list);
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
    
    private static String[][] addEdgeToMatrix(String[][] matrix, int[] edge, boolean isDirected){
        int firstNode = edge[0];
        int secondNode = edge[1];
        if( isDirected ){
            matrix[firstNode -1][secondNode -1] = "1";
        }else {
            matrix[firstNode - 1][secondNode - 1] = "1";
            matrix[secondNode - 1][firstNode - 1] = "1";
        }
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
