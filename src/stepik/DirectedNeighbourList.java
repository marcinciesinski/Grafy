package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedNeighbourList {
    public static void main(String[] args) {
        List<String> inputList = getGraphInput();
        Map<Integer, Integer> countOutMap = countOutDegree(inputList);
        Map<Integer, Integer> countInMap = buildMapInDegree(inputList);
        Integer[] tempNodeArray = buildTempArray(inputList);
        countInDegree(inputList,countInMap,tempNodeArray);
        printInAndOutNodeDegree(countInMap, countOutMap);
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
    
    private static Map<Integer, Integer> countOutDegree(List<String> inputList){
        Map<Integer, Integer> counterOutMap = new HashMap<>();
        for(Integer i = 1; i < inputList.size() + 1 ; i++) {
            Integer amount = inputList
                            .get(i - 1)
                            .split(" ")
                            .length -1 ;
            counterOutMap.put(i,amount);
        }
        return counterOutMap;
    }
    
    private static Map<Integer, Integer> buildMapInDegree(List<String> inputList){
        Map<Integer, Integer> counterInMap = new HashMap<>();
        for(int i = 1; i < inputList.size()+1; i++) {
            counterInMap.put(i, 0);
        }
        return counterInMap;
    }
    
    private static Integer[] buildTempArray(List<String> inputList){
        Integer[] tempNodeArray = new Integer[inputList.size()];
        for(int i = 0; i < tempNodeArray.length; i++) {
            tempNodeArray[i] = i+1;
        }
        return tempNodeArray;
    }
    
    private static void countInDegree(List<String> inputList, Map<Integer, Integer> counterInMap, Integer[] tempNodeArray){
        for( String line : inputList){
            String[] tempArray = line.split(" ");
            for(int i = 1; i < tempArray.length; i++) {
                Integer value = counterInMap.get(Integer.parseInt(tempArray[i]));
                counterInMap.put(Integer.parseInt(tempArray[i]), value+1);
            }
        }
    }
    
    private static void printInAndOutNodeDegree (Map<Integer, Integer> counterInMap, Map<Integer, Integer> counterOutMap){
        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        counterInMap.forEach((k , v) -> {
            in.append(v);
            in.append(" ");
        });
        counterOutMap.forEach((k , v) -> {
            out.append(v);
            out.append(" ");
        });
        System.out.println("Stopnie wejściowe: " + in.toString().trim());
        System.out.println("Stopnie wyjściowe: " + out.toString().trim());
    }
}
