package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeGraph {
    public static void main(String[] args) {
        List<String> inputList = getGraphInput();
        String[][] inputMatrix = buildMatrix(inputList);

        fillMatrixFromList(inputList, inputMatrix);
        printMatrix(inputMatrix, inputMatrix.length);

        Map<Integer, List<String>> map = buildEdgeGraphAsMap(inputMatrix);
        map.forEach((k , v) -> {
            System.out.println(k +" "+ v);
        });
        System.out.println();

        List<String> returnList = pritnEdgeGraphListFromMap(map, inputList);
        printEdgeList(returnList);
    }

    private static List<String> getGraphInput() {
        List<String> listOfLines = new ArrayList<>();
        try (InputStreamReader in = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(in)) {
            String inputLine;
            while (!(inputLine = br.readLine()).equals("")) {
                listOfLines.add(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
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

    private static void printMatrix(String[][] matrixToPrint, int numberOfNodes) {
        int index = 0;
        for (String[] lines : matrixToPrint){
            for (String s : lines){
                System.out.printf(s);
                if (index != numberOfNodes -1){
                    System.out.printf(" ");
                }
                index++;
            }
            System.out.println();
            index =0;
        }
    }

    private static Map<Integer, List<String>> buildEdgeGraphAsMap(String[][] matrix) {
        Integer lineCounter = 1;
        Map<Integer, List<String>> returnEdgeGraph = new HashMap<>();
        for(String[] line : matrix){
            List<String> nodes = new ArrayList<>();
            for (int i = 1; i < line.length; i++) {
                if( line[i].equals("1") && (!returnEdgeGraph.keySet().contains(i))){
                    nodes.add(i+1+"");
                    returnEdgeGraph.put(lineCounter, nodes);
                }
            }
            lineCounter++;
        }
        return returnEdgeGraph;
    }

    private static List<String> pritnEdgeGraphListFromMap (Map<Integer, List<String>> graphMap, List<String> inputList){
        List<String> printList = new ArrayList<>();
        int counter=1;
        for(String line : inputList){
            if(counter == inputList.size())
                break;
            StringBuilder sb = new StringBuilder();
            String[] tempArray = line.split(" ");
            for (int i = 0; i < tempArray.length; i++) {
                if(!graphMap.containsKey(Integer.parseInt(tempArray[i])))
                    break;
//                List<String> tempList = graphMap.get(Integer.parseInt(tempArray[0]));
//                System.out.println(graphMap.keySet().toArray()[i]);
//                System.out.println(graphMap.get(i+1));
                for (int k = 0; k < graphMap.get(Integer.parseInt(tempArray[i])).size(); k++) {
//                    System.out.println(tempArray[i]);
                    if(graphMap.containsKey(Integer.parseInt(tempArray[i]))) {
                        sb.append("(" + graphMap.keySet().toArray()[Integer.parseInt(tempArray[i]) - 1] + ", " + graphMap.get(Integer.parseInt(tempArray[i])).get(k) + ")");
                        sb.append(" ");
                    }
                }
            }
            printList.add(sb.toString().trim());
            counter++;
        }
        return printList;
    }

    private static void printEdgeList(List<String> list){
        for(String s : list){
            System.out.println(s);
        }
    }
}
