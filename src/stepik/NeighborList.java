package stepik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class NeighborList {
    public static void main(String[] args) {
        List<String> list = getGraphInput();
        System.out.println(countAverageDegreeOfGraph(list));
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
    
    private static double countAverageDegreeOfGraph(List<String> list){
        double counter = 0;
        double elem = list.size();
        for(String lines : list){
            String[] elements = lines.split(" ");
            counter += elements.length -1;
        }
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", dfs);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.valueOf(df.format(counter/elem));
    }
}


