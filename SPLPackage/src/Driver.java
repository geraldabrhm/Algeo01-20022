import java.util.Scanner;

import java.util.*;

public class Driver{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<ArrayList<Double>> inputmatrix = new ArrayList<>();
        int nrow=in.nextInt();
        int ncol=in.nextInt();
        for(int i=0;i<nrow;i++){
            ArrayList<Double> temp = new ArrayList<>();
            for(int j=0;j<ncol;j++){
                double x=in.nextDouble();
                temp.add(j,x);
            }
            inputmatrix.add(i,temp);
        }
        GaussJordanMatrix cek=new GaussJordanMatrix(inputmatrix, nrow, ncol);
        for(int i=0;i<nrow;i++){
            for(int j=0;j<ncol;j++){
                System.out.print(cek.getJordanMatrix().get(i).get(j)+" ");
            }
            System.out.println();
        }
        System.out.println(cek.divVal);
    }
}