package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ZadanieZlozone {
    
    public static void main(String[] args) {
    
        List<String> test = getInput();
        int verticles = countVertices(test);
        String[][] matrix = buildMatrix(test);
        int edges = countEdgesInMatrix(matrix);
        int[] degrees = countDegreesOfVerticles(matrix);
        System.out.println("Ilość wierzchołków: "+ verticles);
        System.out.println("Ilość krawędzi: " + edges);
        System.out.println("Stopnie wierzchołków: " + printDegrees(degrees));
        System.out.println("Średni stopień: " + averageDegrees(degrees));
        System.out.println(checkForType(matrix));
    
    
    }
    
    private static List<String> getInput(){
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
    
    private static int countEdgesInMatrix(String[][] matrix){
        int count =0;
        for (String[] subArray : matrix){
            for (String content : subArray){
                if( !content.equals("0") ){
                    count++;
                }
            }
        }
        return count/2;
    }
    
    private static int[] countDegreesOfVerticles(String[][] matrix){
        int size = matrix.length, marker = 0, counter=0;
        int[] result = new int[size];
        for(String[] subArray: matrix){
            for(String content : subArray){
                if(!content.equals("0")){
                        counter++;
                }
            }
            result[marker] = counter;
            counter =0;
            marker++;
        }
        return result;
    }
    
    private static String printDegrees(int[] degrees){
        String result;
        StringBuilder sb = new StringBuilder();
        for(int d : degrees) {
            sb.append(d + " ");
        }
        result = sb.toString().trim();
        return result;
    }
    
    private static double averageDegrees(int[] degrees){
        double average, sum = 0;
        for(int d : degrees){
            sum += d;
        }
        average = sum/degrees.length;
        return average;
    }
    
    private static boolean isFullGraph(String[][] matrix){
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(i != j && matrix[i][j].equals("0") && matrix[j][i].equals("0"))
                    return false;
            }
        }
        return true;
    }
    
    private static boolean isPathGraph(String[][] matrix){
        firstLoop:
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
            
            }
            
        }
        return true;
    }
    
    private static boolean isCyclicGraph(String[][] matrix){
        
        
        return true;
    }
    
    private static String checkForType(String[][] matrix){
        if( isFullGraph(matrix))
            return "Jest to graf pełny.";
        
        return "Graf nie należy do żadnej z podstawowych klas.";
    }
}
