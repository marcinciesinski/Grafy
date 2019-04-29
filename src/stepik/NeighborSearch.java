package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NeighborSearch {
    
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        int node = getNumberOfNode(list);
        int nodeNumber = countVertices(list);
        String[][] matrix = buildMatrix(list);
        String result = findNeighborOfNode(matrix, node, nodeNumber);
        System.out.println(result);
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
    
    private static int countVertices(List<String> list){
        return list.size();
    }
    
    private static int getNumberOfNode ( List<String> list){
        String node = list.remove(list.size() -1);
        return Integer.parseInt(node);
    }
    
    private static boolean isNodeCorrect(int node, int nodeNumbers){
        return node >= 1 && node <= nodeNumbers;
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
    
    private static String findNeighborOfNode(String[][] matrix, int node, int nodeNumber){
        if ( !isNodeCorrect(node, nodeNumber))
            return "BŁĄD";
        else {
            int neighborNumber = 1;
            String output = "";
            String[] nodeArray = matrix[node - 1];
            for(String s : nodeArray) {
                if(!s.equals("0")) {
                    output += neighborNumber + " ";
                }
                neighborNumber++;
            }
            return output.trim();
        }
    }
}
