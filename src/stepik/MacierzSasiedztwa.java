package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MacierzSasiedztwa {
    public static void main(String[] args) {
        
        List<String > listOfLines = getInput();
        int numberOfVertices = countVertices(listOfLines);
        int numberOdEdge = countEdgesInMatrix(listOfLines);
    
        System.out.printf("Ilość wierzchołków: %d\nIlość krawędzi: %d", numberOfVertices, numberOdEdge);
    }
    
    private static List<String> getInput(){
        List<String> listOfLines = new ArrayList<>();
        try(InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in)){
            String inputLine;
            while ( (inputLine = br.readLine()) != null ){
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
    
    private static int countEdgesInMatrix(List<String> listOfLines){
        int size = listOfLines.size();
        int index = 0, count = 0;
        String[][] matrix = new String[size][size];
        
        for(String lines : listOfLines){
            matrix[index] = lines.split(" ");
            index++;
        }
    
        for (String[] subArray : matrix){
            for (String content : subArray){
                if( !content.equals("0") ){
                    count++;
                }
            }
        }
        return count/2;
    }
}
