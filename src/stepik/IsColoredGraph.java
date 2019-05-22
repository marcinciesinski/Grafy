package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsColoredGraph {
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        String[] lastLine = getLastLine(list);
        Map<Integer, String> nodeWithColorMap = nodeWithColorMap(list, lastLine);
        System.out.println(isColoredGraph(list, nodeWithColorMap)? "Graf jest kolorowalny" : "Graf nie jest kolorowalny");
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
    
    private static String[] getLastLine(List<String> list){
        String[] result = list.get(list.size()-1).split(" ");
        list.remove(list.size()-1);
        return result;
    }
    
    private static Map<Integer, String> nodeWithColorMap (List<String> list, String[] lastLine){
        list.remove(list.size()-1);
        Map<Integer, String> resultMap = new HashMap<>();
        for(int i = 0; i < list.size() ; i++)
            resultMap.put((i+1),lastLine[i]);
        return resultMap;
    }
    
    private static boolean isColoredGraph(List<String> list, Map<Integer, String> nodeWithColorMap) {
        int counter = 1;
        for(String line : list) {
            String[] tempArray = line.split(" ");
            for(int i = 1; i < tempArray.length; i++) {
                if(nodeWithColorMap.get(counter).equals(nodeWithColorMap.get(Integer.parseInt(tempArray[i]))))
                    return false;
            }
            counter++;
        }
        return true;
    }
}
