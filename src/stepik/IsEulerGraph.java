package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class IsEulerGraph {
    
    public static void main(String[] args) {
        
        List<String> list = getGraphInput();
        List<Integer> dfsNodes = new ArrayList<>();
        boolean[] visited = new boolean[list.size()];
        Stack stack = new Stack();
        int startingNode = new Random().nextInt(list.size());
        if(isConnectedGraph(list,startingNode,dfsNodes,visited,stack)){
            String[][] matrix = buildMatrix(list);
            if(isEulerGraph(matrix)){
                System.out.println("Graf jest eulerowski");
            }else if(isHalfEulerGraph(matrix)){
                System.out.println("Graf jest półeulerowski");
            }else {
                System.out.println("Graf nie jest eulerowski");
            }
        }else {
            System.out.println("Graf jest niespójny");
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
    
    private static boolean isConnectedGraph(List<String> list, int startingNode, List<Integer> dfsNodes, boolean[] visited, Stack stack){
        visited[startingNode] = true;
        stack.push(startingNode);
        dfsNodes.add(startingNode + 1);
        while(!stack.empty()){
            String[] array = list.get(startingNode).split(" ");
            for(int i = 0; i < array.length; i++) {
                if (array[i].equals("1") && !visited[i]){
                    visited[i]= true;
                    isConnectedGraph(list, i, dfsNodes, visited, stack);
                }else if(array[i].equals("1") && visited[i]){
                    continue;
                }
            }
            if(!stack.empty()){
                stack.pop();
            }
        }
        return dfsNodes.size() == list.size();
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
    
    private static boolean isEulerGraph(String[][] matrix) {
        return isEachVertexEven(matrix);
    }
    
    private static boolean isEachVertexEven(String[][] matrix) {
        for(String[] subArray : matrix) {
            int counter = 0;
            for(String s : subArray) {
                if(s.equals("1"))
                    counter++;
            }
            if(counter % 2 != 0)
                return false;
        }
        return true;
    }
    
    private static boolean isHalfEulerGraph(String[][] matrix){
        return isAtMostTwoVertexOdd(matrix);
    }
        
    private static boolean isAtMostTwoVertexOdd(String[][] matrix){
        int counter2=0;
        for(String[] subArray : matrix) {
            int counter = 0;
            for(String s : subArray) {
                if(s.equals("1"))
                    counter++;
            }
            if(counter % 2 != 0)
                counter2++;
        }
        return counter2 <= 2;
    }
}
