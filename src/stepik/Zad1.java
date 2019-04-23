package stepik;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Zad1
{

    public static void main(String[] args) {
        String input = getInput();
        String[] reversed = reversedInput(input);
        printReversedInput(reversed);
    }
    
    public static String getInput(){
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return result;
    }
    
    public static String[] reversedInput(String input){
        String[] arrayToReverse = input.split(" ");
        Collections.reverse(Arrays.asList(arrayToReverse));
        return arrayToReverse;
    }
    
    public static void printReversedInput(String[] array){
        int iterator = array.length -1;
        for(String s : array){
            System.out.print(s);
            iterator--;
            if(iterator >= 0) {
                System.out.print(" ");
            }
        }
    }
}
