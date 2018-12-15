package ch.raphigassmann.sudokuSolver;

import java.util.*;

public class Controller {

    public static int counter = 0;

    public static void solve(int[][] input){
        System.out.println("running");
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] != 0){
                    System.out.println(input[i][j]);

                }else{
                    getPossibilities(input);
                }
            }
        }



    }

    public static int[][][] getPossibilities(int[][] input){
        int[][][] possibilities = new int[8][8][8];

        //Traverse all members
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                counter = 0;
                if (input[i][j] == 0) {
                    //System.out.println("Checking for possible Numbers at: "+i+" and "+j);
                    //TODO Check


                    //Check Row

                    Collection<Integer> possRow = new ArrayList(Arrays.asList());
                    for (int k = 0; k < input.length; k++) {
                        if(input[i][k] != 0){
                            //add possibility
                            possRow.add(k);
                        }
                    }

                    //Check Column
                    Collection<Integer> possClm = new ArrayList(Arrays.asList());
                    for (int k = 0; k < input.length; k++) {
                        if(input[k][j] != input[i][j] && input[k][j] != 0){
                            //add possibility
                            possClm.add(k);

                        }
                    }

                    //Check Quad


                    Collection<Integer> similar = new HashSet<Integer>(possClm);
                    similar.retainAll(possRow);
                    System.out.printf("Similar:%s%n", similar);



                }else{
                    //System.out.println("Already fixed number at: "+i+" and "+j+", with value: "+input[i][j]);
                }

            }
        }
        return possibilities;
    }


}
