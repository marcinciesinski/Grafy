package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GrafSkierowany {
    
    public static void main(String[] args) {
        
        List<String> listOfLines = getInput();
        System.out.println(isDirectedGraph(listOfLines) ? "Graf jest skierowany" : "Graf jest nieskierowany");
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
    
    private static boolean isDirectedGraph(List<String> listOfLines){
        int size = listOfLines.size();
        int index = 0, marker;
        boolean flag = false;
        String[][] matrix = new String[size][size];
        
        for(String lines : listOfLines){
            matrix[index] = lines.split(" ");
            index++;
        }
        
        subArrayLoop:
        for(int i = 0; i < size; i++) {
            marker = i ;
            for(int j = 0; j < size; j++) {
                if( !matrix[i][j].equals("0")){
                    continue subArrayLoop;
                }
            }
            for(int j = 0; j < size; j++) {
                if( !matrix[j][marker].equals("0"))
                    flag = true;
            }
        }
        return flag;
    }
}
