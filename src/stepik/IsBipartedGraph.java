package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class IsBipartedGraph {
    public static void main(String[] args) {
        List<String> inputList = getGraphInput();
        int[] colorArray = colorArray(inputList);
        int startingNode = 0;
        int previousNode = -1;
        String[][] matrix = buildMatrix(inputList);
        fillMatrixFromList(inputList, matrix);
        List<String> matrixAsList = matrixAsList(matrix);
        List<Integer> dfsNodes = new ArrayList<>();
        boolean[] visited = new boolean[inputList.size()];
        Stack stack = new Stack();
        deepFirstSearch(matrixAsList, startingNode,dfsNodes,visited,stack,colorArray,previousNode);
        if(isBipartedGraph(inputList,colorArray)){
            System.out.println("Graf jest dwudzielny");
            printPartition(colorArray);
        }else {
            System.out.println("Graf nie jest dwudzielny");
        }
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
    
    private static int[] colorArray(List<String> list){
        return new int[list.size()];
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
    
    private static List<String> matrixAsList(String[][] matrix){
        List<String> matrixAsList = new ArrayList<>();
        for(String[] subArray : matrix){
            StringBuilder line = new StringBuilder();
            for(String s : subArray){
                line.append(s);
                line.append(" ");
            }
            matrixAsList.add(line.toString().trim());
        }
        return matrixAsList;
    }
    
    private static boolean deepFirstSearch(List<String> list, int startingNode, List<Integer> dfsNodes, boolean[] visited, Stack stack, int[] colorArray, int previousNode){
        if(startingNode == 0 && previousNode == -1) {
            colorArray[startingNode] = 1;
        }
        previousNode = startingNode;
        visited[startingNode] = true;
        stack.push(startingNode);
        dfsNodes.add(startingNode + 1);
        while(!stack.empty()){
            String[] array = list.get(startingNode).split(" ");
            for(int i = 0; i < array.length; i++) {
                if (array[i].equals("1") && !visited[i]){
                    visited[i]= true;
                    colorArray[i] = -colorArray[previousNode];
                    deepFirstSearch(list, i,dfsNodes, visited, stack, colorArray, previousNode);
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
    
    private static boolean isBipartedGraph(List<String> inputList, int[] colorArray){
        int counter = 0;
        for( String line : inputList){
            String[] tempArray = line.split(" ");
            for(int i = 1; i < tempArray.length; i++) {
                if(colorArray[counter] == colorArray[Integer.parseInt(tempArray[i]) -1 ]){
                    return false;
                }
            }
            counter++;
        }
        return true;
    }
    
    private static void printPartition (int[] colorArray){
        long first = Arrays.stream(colorArray).filter( i -> i == 1).count();
        long second = Arrays.stream(colorArray).filter(i -> i == -1).count();
        StringBuilder smaller = new StringBuilder();
        StringBuilder larger = new StringBuilder();
        if ( first < second){
            for(int i = 0; i < colorArray.length; i++) {
                if(colorArray[i] == 1) {
                    smaller.append(i+1);
                    smaller.append(" ");
                }else {
                    larger.append(i+1);
                    larger.append(" ");
                }
            }
        }else if ( second < first) {
            for(int i = 0; i < colorArray.length; i++) {
                if(colorArray[i] == -1) {
                    smaller.append(i+1);
                    smaller.append(" ");
                }else {
                    larger.append(i+1);
                    larger.append(" ");
                }
            }
        }
        System.out.println("Pierwsza partycja: "+ smaller.toString().trim());
        System.out.println("Druga partycja: "+ larger.toString().trim());
    }
}
