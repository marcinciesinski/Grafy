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
        System.out.println(averageDegrees(degrees));
        System.out.println(checkForType(matrix,degrees, edges));
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
            sb.append(d);
            sb.append(" ");
        }
        result = sb.toString().trim();
        return result;
    }
    
    private static String averageDegrees(int[] degrees){
        double average, sum = 0;
        for(int d : degrees){
            sum += d;
        }
        average = sum/degrees.length;
        if (average % 1.0 ==0){
            return "Średni stopień: " + (int)average;
        }else {
            return "Średni stopień: " + average;
        }
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
    
    private static boolean isPathGraph(int [] degreesArray, int edges){
        if ( degreesArray.length -1 != edges)
            return false;
        for(int i = 1; i < degreesArray.length; i++) {
            if(degreesArray[0] != 1){
                return false;
            }else if (degreesArray[i] != 2 && i != degreesArray.length -1){
                return false;
            }else if (degreesArray[degreesArray.length -1] != 1){
                return false;
            }
        }
        return true;
    }
    
    private static boolean isCyclicGraph(int[] degreesArray, int edges){
        if( degreesArray.length != edges)
            return false;
        for(int d : degreesArray){
            if( d != 2){
                return false;
            }
        }
        return true;
    }
    
    private static boolean isTreeGraph( int edges, int[] degreesArray){
        if( degreesArray.length -1 != edges)
            return false;
        else if(degreesArray[0] != 2)
            return false;
        for(int i = 1; i < degreesArray.length; i++) {
            if(degreesArray[i] != 1 && degreesArray[degreesArray.length -1] != 1)
                return false;
        }
        return true;
    }
    
    private static String checkForType(String[][] matrix,int[] degreesArray, int edges){
        if( isFullGraph(matrix))
            return "Jest to graf pełny.";
        else if( isCyclicGraph(degreesArray, edges))
            return "Jest to cykl.";
        else if( isPathGraph( degreesArray, edges))
            return "Jest to ścieżka.";
        else if( isTreeGraph(edges, degreesArray))
            return "Jest to drzewo.";
        return "Graf nie należy do żadnej z podstawowych klas.";
    }
}
