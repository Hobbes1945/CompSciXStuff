import com.tychovonr.Matrix.Matrix;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void idMatrixTest(){
        Matrix m = new Matrix(2,2);
        Matrix id = m.idMatrix(2);
        assertThat(id.getEntry(1,1), is(1.0));
        assertThat(id.getEntry(0,1), is(0.0));
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
        m = m.add(id);
        assertThat(m.getEntry(0,0), is(1.0));
        assertThat(m.getEntry(4,4), is(9.0));

    }
    @Test
    public void matrixProdTest(){
        Matrix m = new Matrix(2,4);
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j,i+j+1);
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
                m.setEntry(i,j,i*i+j*j+11);
            }
        }
        System.out.println(m.complexDeterminant());
        System.out.println(m.toString());
        System.out.println(m.invert().toString());
        System.out.println(m.times(m.invert()).matrixClean());
        assertThat(m.getEntry(2,2), is(18.0));

    }
    @Test
    public void switchRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, i + j);
            }
        }
        System.out.println(m);
        System.out.println(m.switchRows(1, 3));
        System.out.println(m);
    }
    @Test
    public void scalarTimesRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, i + j);
            }
        }
        assertThat(m.getEntry(3,3), is(18.0));
        assertThat(m.getEntry(3,2), is(5.0));
    }
    @Test
    public void linearComboRows() {
        Matrix m = new Matrix(4, 4);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, 1);
            }
        }
        assertThat(m.getEntry(3,3), is(5.0));
        assertThat(m.getEntry(0,0), is(1.0));
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
        m = m.sortZeroes();
        assertThat(m.getEntry(4,4), is(0.0));
        assertThat(m.getEntry(4,5), is(0.0));
        assertThat(m.getEntry(4,6), is(0.0));
        assertThat(m.getEntry(4,7), is(0.0));
    }
    @Test
    public void ComplexDeterminant(){
        Matrix m = new Matrix(3, 3);
        int x = 1;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                m.setEntry(j,i,x);
                x++;
            }
        }
        System.out.println(m.toString());
        System.out.println(m.complexDeterminant());
        assertThat(m.complexDeterminant(), is(0.0));
    }
    @Test
    public void rowReduce() {
        Matrix m = new Matrix(3, 3);
        int x = 1;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                m.setEntry(j,i,x);
                x++;
            }
        }
        System.out.println(m.toString());
        System.out.println(m.rowReduce().matrixClean());
        assertThat(m.rowReduce().matrixClean(), is(32));
    }
    @Test
    public void derive() {
        double[] poly = new double[4];
        poly[1] = 3;
        poly[3] = 2;
        Matrix m = new Matrix(poly.length,poly.length);
        double x = m.derive(poly,-1);
        System.out.println(x);
        assertThat(x, is(9.0));
    }
}
