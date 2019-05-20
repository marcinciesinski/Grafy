package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MatrixToList {
    public static void main(String[] args) {
        
        List<String> inputList = getGraphInput();
        List<String> resultList = transformMatrixToList(inputList);
        printList(resultList);
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
    
    private static List<String> transformMatrixToList(List<String> inputList) {
        List<String> returnList = new ArrayList<>();
        int nodeCounter = 1;
        for(String lines : inputList) {
            StringBuilder line = new StringBuilder();
            line.append(nodeCounter);
            line.append(" ");
            int counter = 1;
            String[] tempArray = lines.split(" ");
            for(String node : tempArray){
                if(node.equals("1")){
                    line.append(counter);
                    line.append(" ");
                }
                counter++;
            }
            nodeCounter++;
            returnList.add(line.toString().trim());
        }
        return returnList;
    }
    
    private static void printList(List<String> returnList){
        for(String line : returnList)
            System.out.println(line);
    }
}