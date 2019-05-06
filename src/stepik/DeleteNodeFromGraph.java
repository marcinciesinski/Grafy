package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteNodeFromGraph {
    
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        String[] lastLine = getLastFromList(list);
        int numberOfNodes = numberOfNodes(list);
        int node = convertToInt(lastLine);
        if(isCorrectNode(node,numberOfNodes)){
            deleteNodeFromList(list, node);
            String[][] matrix = buildMatrix(list, node);
            printMatrix(matrix, numberOfNodes -1);
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
    
    private static String[] getLastFromList(List<String> list){
        String[] buffer = list
            .remove(list.size()-1)
            .split(" ");
        return buffer;
    }
    
    private static int numberOfNodes(List<String> list){
        return list.size();
    }
    
    private static boolean isCorrectNode(int node, int numberOfNodes){
        return node > 0 && node <= numberOfNodes;
    }
    
    private static int convertToInt(String[] lastLine){
        return Integer.parseInt(lastLine[0]);
    }
    
    private static void deleteNodeFromList(List<String> list, int node){
        list.remove(node - 1 );
    }
    
    private static String[][] buildMatrix(List<String> listOfLines, int node){
        int size = listOfLines.size();
        String[][] matrix = new String[size][size];
    
        for(int i = 0; i < size ; i++) {
            String[] tempArray = listOfLines
                .get(i)
                .split(" ");
            List<String> tempList = new ArrayList<>(Arrays.asList(tempArray));
            tempList.remove(node - 1 );
            tempArray = tempList.toArray(new String[tempList.size()]);
            for(int j = 0; j < size; j++) {
                matrix[i][j] = tempArray[j];
            }
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
