import com.tychovonr.Matrix.Matrix;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class MatrixTest {
    @Test
    public void idMatrixTest(){
        Matrix m = new Matrix(5,5);
        Matrix id = m.idMatrix(m.getCol());
        System.out.println(id.toString());
    }
    @Test
    public void matrixAddTest(){
        Matrix m = new Matrix(5,5);
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j,i+j);
            }
        }
        Matrix id = m.idMatrix(m.getCol());
        System.out.println(m.add(id).toString());
    }
    @Test
    public void matrixProdTest(){
        Matrix m = new Matrix(2,4);
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j,i+j);
            }
        }
        System.out.println(m.toString());
        System.out.println("");
        Matrix m2 = new Matrix(4,2);
        for (int i = 0; i <m2.getCol() ; i++) {
            for (int j = 0; j <m2.getRow() ; j++) {
                m2.setEntry(i,j,1);
            }
        }
        System.out.println(m2.toString());
        System.out.println("");
        System.out.println(m.times(m2).toString());
    }
    @Test
    public void matrixInvTest(){
        Random gen= new Random();
        Matrix m = new Matrix(3,3);
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j,gen.nextInt(50));
            }
        }
        System.out.println(m.SimpleDeterminant());
        System.out.println(m.toString());
        System.out.println(m.invert().toString());

    }
    @Test
    public void switchRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, i + j);
            }
        }
        System.out.println(m.switchRows(1, 3));
    }
    @Test
    public void scalarTimesRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, i + j);
            }
        }
        System.out.println(m.scalarTimesRow(3, 3));
    }
    @Test
    public void linearComboRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, 0);
            }
        }
        System.out.println(m.linearComboRows(4,1, 3));
    }
    @Test
    public void MatrixBreaker() {
        Random gen= new Random();
        Matrix m = new Matrix(4, 4);

        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, gen.nextInt(50));
            }
        }
        System.out.println(m.toString());
        System.out.println(m.matrixBreaker(0,0).matrixBreaker(0,0).SimpleDeterminant());
    }
    @Test
    public void sortZeroes() {
        Random gen= new Random();
        Matrix m = new Matrix(6, 8);
        for (int i = 0; i < m.getCol(); i++) {
            if (i%2==0){
                for (int j = 0; j < m.getRow(); j++) {
                    if (j%2 ==0){
                        m.setEntry(i, j, gen.nextInt(50));
                    }
                    else {
                        m.setEntry(i,j,0);
                    }
                }
            }
            else {
                for (int j = 0; j < m.getRow(); j++) {
                    m.setEntry(i,j, 0);
                }
            }
        }
        System.out.println(m.toString());
        m = m.sortZeroes();
        System.out.println(m.toString());
        System.out.println("rows" + m.getBlankRow() + ", cols" + m.getBlankCol());
    }
    @Test
    public void ComplexDeterminant(){
        Random gen= new Random();
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, gen.nextInt(50));
            }
        }
        System.out.println(m.toString());
        System.out.println(m.ComplexDeterminant());
    }
    @Test
    public void TestMatrix(){
        Random gen = new Random();
        Matrix m = new Matrix(gen.nextInt(10),gen.nextInt(10));
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j,j);
            }
        }
        System.out.println(m.toString());
        System.out.println("rows" + m.getRow() + ", cols" + m.getCol());
    }
    @Test
    public void rowReduce() {
        Random gen = new Random();
        Matrix m = new Matrix(6, 8);
        for (int i = 0; i < m.getCol(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < m.getRow(); j++) {
                    if (j % 2 == 0) {
                        m.setEntry(i, j, gen.nextInt(50));
                    } else {
                        m.setEntry(i, j, 0);
                    }
                }
            } else {
                for (int j = 0; j < m.getRow(); j++) {
                    m.setEntry(i, j, 0);
                }
            }

        }
        System.out.println(m.toString());
        System.out.println(m.rowReduce());
    }
}
