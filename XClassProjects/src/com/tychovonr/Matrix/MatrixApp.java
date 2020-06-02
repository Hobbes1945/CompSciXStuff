package com.tychovonr.Matrix;
import java.util.Scanner;
public class MatrixApp {
    public static void main(String[] args) {
        boolean go = true;
        while (go){
            Scanner in = new Scanner(System.in);
            System.out.println("1 for matrix, 2 for polynomial");
            String s = in.nextLine();
            if (s.equals("1")){
                System.out.println("How many cols");
                s = in.nextLine();
                int col = Integer.parseInt(s);

                System.out.println("How many rows");
                s = in.nextLine();
                int row = Integer.parseInt(s);
                Matrix m = new Matrix(col,row);
                for (int i = 0; i <col ; i++) {
                    for (int j = 0; j <row ; j++) {
                        System.out.println("Enter a double");
                        s = in.nextLine();
                        double val = Double.parseDouble(s);
                        m.setEntry(i,j,val);
                    }
                }
                System.out.println(m);
                System.out.println(" Inverted" + "\n" + m.invert());
                System.out.println("Inverted times Matrix" +"\n"+ m.times(m.invert()).matrixClean());
                System.out.println("Determinant" + m.complexDeterminant());
            }else if (s.equals("2")){
                System.out.println("What degree is the polynomial");
                s = in.nextLine();
                int val = Integer.parseInt(s);
                double[] coef = new double[val];
                for (int i = 0; i <val ; i++) {
                    System.out.println("Coeffecient for x^" +i);
                    s = in.nextLine();
                    coef[i] = Double.parseDouble(s);
                }
                for (int i = 0; i <val ; i++) {
                    System.out.print(coef[i] + "x^" + i + " + " );
                }
                System.out.println("");
                System.out.println("At what x value");
                s = in.nextLine();
                double num = Double.parseDouble(s);
                Matrix m = new Matrix(coef.length,coef.length);
                System.out.println(m.vdmMaker(coef,num));
                System.out.println("derivative" + m.derive(coef,num));
            }
        }
    }
}
