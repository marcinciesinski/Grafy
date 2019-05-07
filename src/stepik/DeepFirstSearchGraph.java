package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeepFirstSearchGraph {
    
    
    
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        int startingNode = getLastFromList(list);
        List<Integer> dfsNodes = new ArrayList<>();
        Stack stack = new Stack();
        boolean[] visited = new boolean[list.size()];
        if( startingNode + 1 < 1 || startingNode + 1 > list.size()){
            System.out.println("BŁĄD");
        }else {
            System.out.println(deepFirstSearch(list, startingNode, dfsNodes, visited, stack) ? "Porządek DFS: " + printNodes(dfsNodes) + "\n" +
                                                                                               "Graf jest spójny" : "Graf jest niespójny");
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
    
    private static int getLastFromList(List<String> list){
        String[] buffer = list
            .remove(list.size()-1)
            .split(" ");
        return Integer.parseInt(buffer[0]) -1;
    }
    
    private static boolean deepFirstSearch(List<String> list, int startingNode, List<Integer> dfsNodes, boolean[] visited, Stack stack){
        visited[startingNode] = true;
        stack.push(startingNode);
        dfsNodes.add(startingNode + 1);
        while(!stack.empty()){
            String[] array = list.get(startingNode).split(" ");
            for(int i = 0; i < array.length; i++) {
                if (array[i].equals("1") && !visited[i]){
                    visited[i]= true;
                    deepFirstSearch(list, i,dfsNodes, visited, stack);
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
    
    private static String printNodes(List<Integer> dfsNodes){
        String result = "";
        for(Integer i : dfsNodes){
            result += i + " ";
        }
        return result.trim();
    }
}
