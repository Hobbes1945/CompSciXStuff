package com.tychovonr.Matrix;
import java.util.LinkedList;
import java.util.Queue;
public class Matrix {
    double matrix[][];
    int col;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    int row;
    public Matrix(int col, int row) {
        this.col = col;
        this.row = row;
        double[][] tempAr = new double[col][row];
        this.matrix = tempAr;
    }

    public double getEntry (int x, int y){
        return matrix[x][y];
    }
    public void setEntry (int x, int y, double val){
        matrix[x][y] = val;
    }
    public Matrix add(Matrix m){
        Matrix addM = new Matrix(this.col, this.row);
        for (int i = 0; i <this.col; i++) {
            for (int j = 0; j <this.col ; j++) {
                addM.setEntry(i,j, this.getEntry(i,j)+m.getEntry(i,j));
            }
        }
        return addM;
    }
    public Matrix idMatrix(int sideLength){
        Matrix idM= new Matrix(sideLength,sideLength);
        for (int i = 0; i < sideLength ; i++) {
            idM.matrix[i][i] = 1;
        }
        return idM;
    }
    public Matrix matrixDupe(Matrix m){
        Matrix dupM = new Matrix(this.col,this.row);
        for (int i = 0; i < this.col ; i++) {
            for (int j = 0; j < this.row; j++) {
                dupM.matrix[i][j] = m.matrix[i][j];
            }
        }
        return dupM;
    }
    public Matrix switchRows (int firstRow, int secondRow){
        Queue<Double> tempQ = new LinkedList<>();
        Matrix switchM = matrixDupe(this);
        for (int i = 0; i < this.matrix[firstRow].length ; i++) {
            switchM.setEntry(firstRow,i,this.matrix[secondRow][i]);
            switchM.setEntry(secondRow,i,this.matrix[firstRow][i]);
        }
        return switchM;
    }
    public Matrix linearComboRows(double scalar, int firstRow, int secondRow){
        Matrix comboM  = this;
        comboM = comboM.scalarTimesRow(scalar,firstRow);
        for (int i = 0; i <matrix[firstRow].length ; i++) {
            comboM.matrix[secondRow][i] += comboM.matrix[firstRow][i];
        }
        comboM = comboM.scalarTimesRow(1/scalar,firstRow);

        return comboM;
    }
    public Matrix scalarTimesRow(double scalar, int rowNumber){
        Matrix scalM = matrixDupe(this);
        for (int i = 0; i <matrix[rowNumber].length ; i++) {
            scalM.matrix[rowNumber][i] *= scalar;
        }
        return scalM;
    }
    public Double dotProduct (double[] col, double[] row){
        double dProd = 0;
        if (col.length == row.length){
            for (int i = 0; i <col.length ; i++) {
                dProd += col[i] * row[i];
            }
        }
        return dProd;
    }
    double[] Col (int index){
        double[] col = new double[this.matrix.length];
        for (int i = 0; i <col.length ; i++) {
            col[i] = this.matrix[i][0];
        }
        return col;
    }
    public Matrix times (Matrix m){
        Matrix prodM = new Matrix(this.col,m.row);
        for (int i = 0; i < this.col ; i++) {
            for (int j = 0; j <m.row ; j++) {
                double[]mCol = m.Col(j);
                prodM.matrix[i][j] = dotProduct(this.matrix[i], mCol);
            }
        }
        return prodM;
    }
    public Matrix invert (){
        Matrix m = this;
        Matrix id = idMatrix(m.col);
        for (int i = 0; i <m.col ; i++) {
            for (int j = 0; j < m.col; j++) {
                id = id.scalarTimesRow((1/m.getEntry(j,j)),j);
                m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
            }
            for (int j = i+1; j <m.col ; j++) {
                id = id.linearComboRows(-m.getEntry(j,i), i,j );
                m = m.linearComboRows(-m.getEntry(j,i), i,j );
            }
        }

        //should be in row echelon form
        /*
        for (int i = 0; i <m.getCol() ; i++) {
            m = m.linearComboRows(-m.getEntry(i,3), 3,i );
        }
        for (int j = 1; j <m.col ; j++) {
            m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
        }
        for (int i = 0; i <m.getCol()-1 ; i++) {
            m = m.linearComboRows(-m.getEntry(i,2), 2,i );
        }
        for (int j = 2; j <m.col ; j++) {
            m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
        }
        for (int i = 0; i <m.getCol()-2 ; i++) {
            m = m.linearComboRows(-m.getEntry(i,1), 1,i );
        }
        for (int j = 1; j <m.col ; j++) {
            m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
        }

         */
        for (int i = m.getCol()-1; i > 0 ; i--) {
            for (int j = 0; j <i ; j++) {
                id = id.linearComboRows(-m.getEntry(j,i), i,j );
                m = m.linearComboRows(-m.getEntry(j,i), i,j );
            }
            for (int j = 1; j <m.getCol() ; j++) {
                id = id.scalarTimesRow((1/m.getEntry(j,j)),j);
                m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
            }
        }
        return m.times(this);
    }
    public java.lang.String toString(){
        String mString = "";
        for (int i = 0; i < col; i++) {
            mString = mString + "[";
            for (int j = 0; j < row; j++) {
                mString = mString + " " + (this.matrix[i][j]) + " ";
            }
            mString = mString + "]\n";
        }
        return mString;
    }
}
