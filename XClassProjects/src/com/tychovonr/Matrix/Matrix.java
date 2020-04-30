package com.tychovonr.Matrix;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class Matrix {
    double matrix[][];
    int col;
    int row;

    public int getBlankRow() {
        return blankRow;
    }

    public void setBlankRow(int blankRow) {
        this.blankRow = blankRow;
    }

    public int getBlankCol() {
        return blankCol;
    }

    public void setBlankCol(int blankCol) {
        this.blankCol = blankCol;
    }

    int blankRow;
    int blankCol;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


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
        dupM.setBlankCol(this.getBlankCol());
        dupM.setBlankRow(this.getBlankRow());
        for (int i = 0; i < this.col ; i++) {
            for (int j = 0; j < this.row; j++) {
                dupM.matrix[i][j] = m.matrix[i][j];
            }
        }
        return dupM;
    }
    public Matrix switchRows (int firstRow, int secondRow){
        Matrix switchM = matrixDupe(this);
        for (int i = 0; i < switchM.getCol() ; i++) {
            switchM.setEntry(i,firstRow,this.getEntry(i,secondRow));
            switchM.setEntry(i,secondRow,this.getEntry(i,firstRow));
        }
        return switchM;
    }
    public Matrix linearComboRows(double scalar, int firstRow, int secondRow){
        Matrix comboM  = this;
        comboM = comboM.scalarTimesRow(scalar,firstRow);
        for (int i = 0; i <comboM.getCol() ; i++) {
            comboM.matrix[i][secondRow] += comboM.matrix[i][firstRow];
        }
        comboM = comboM.scalarTimesRow(1/scalar,firstRow);

        return comboM;
    }
    public Matrix scalarTimesRow(double scalar, int rowNumber){
        Matrix scalM = this;
        for (int i = 0; i <scalM.getCol() ; i++) {
            scalM.setEntry(i,rowNumber, scalM.getEntry(i,rowNumber) * scalar);
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
    public boolean oopsAllZeroes(double[] row){
        for (int i = 0; i <row.length ; i++) {
            if(row[i] != 0.0){
                return false;
            }
        }
        return true;
    }
    public int CrunchZeroes(double[] row, int startInd){

        for (int i = startInd; i <row.length ; i++) {
            if (row[i]!= 0){
                return i;
            }
        }
        /*
        for (int i = 0; i <startInd ; i++) {
            if (row[i] != 0){
                return i;
            }
        }

         */
        return 0;
    }
    public Matrix sortZeroes(){
        Matrix m = this;
        int swapcounter = 0;
        for (int i = 0; i <m.getRow() ; i++) {
            if (oopsAllZeroes(m.Row(m.getRow()-i-1))==true){
                m= m.switchRows(m.getRow()-i-1, m.getRow()-swapcounter-1);
                swapcounter++;
            }
        }
        m.setBlankRow(swapcounter);
        return m;
    }
    public Matrix rowReduce(){
        Matrix m = matrixDupe(this);
        m = m.sortZeroes();
        ArrayList<int[]> pivots = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i <m.getRow() ; i++) {
            if (offset+i< m.getCol()) {
                //System.out.println("x: " + (i+offset) + " y: " + i);
                if (m.getEntry(i + offset, i) != 0) {
                    int[] chords = new int[2];
                    chords[0] = i+offset;
                    chords[1] = i;
                    pivots.add(chords);
                    m = m.clearDown(i + offset, i);
                } else if (oopsAllZeroes(m.Col(i + offset))) {
                    offset++;
                    i--;
                } else {
                    m = m.switchRows(CrunchZeroes(m.Col(i + offset), i), i);
                    i--;
                }
            }
        }
        System.out.println(offset);
        for (int i = 0; i <pivots.size() ; i++) {
            m = m.clearUp(pivots.get(pivots.size()-i-1)[0],pivots.get(pivots.size()-i-1)[1]);
        }
        return m;
    }
    public Matrix clearDown(int x, int y){
        Matrix m = matrixDupe(this);
        for (int i = 0; i <m.getRow()-y ; i++) {
            m=m.scalarTimesRow(1/m.getEntry(x,y), y);
            if (m.getEntry(x, y+i)!= 0) {
                m = m.linearComboRows(-m.getEntry(x, y + i), y, y+i);
            }
        }
        return m;
    }
    public Matrix clearUp(int x, int y){
        Matrix m = matrixDupe(this);
        for (int i = 0; i < y; i++) {
            m=m.scalarTimesRow(1/m.getEntry(x,y), y);
            if (m.getEntry(x, i)!= 0) {
                m = m.linearComboRows(-m.getEntry(x,i), y, i);
            }
        }
        return m;
    }
    public Matrix idclearDown(Matrix m, int x, int y){
        Matrix id = matrixDupe(this);
        for (int i = 0; i <m.getRow()-y ; i++) {
            id=id.scalarTimesRow(1/m.getEntry(x,y), y);
            m=m.scalarTimesRow(1/m.getEntry(x,y), y);
            if (m.getEntry(x, y+i)!= 0) {
                id = id.linearComboRows(-m.getEntry(x, y + i), y, y+i);
                m = m.linearComboRows(-m.getEntry(x, y + i), y, y+i);
            }
        }
        return id;
    }
    public Matrix idclearUp(Matrix m, int x, int y){
        Matrix id = matrixDupe(this);
        for (int i = 0; i < y; i++) {
            id=id.scalarTimesRow(1/m.getEntry(x,y), y);
            m=m.scalarTimesRow(1/m.getEntry(x,y), y);
            if (m.getEntry(x, i)!= 0) {
                id = id.linearComboRows(-m.getEntry(x,i), y, i);
                m = m.linearComboRows(-m.getEntry(x,i), y, i);
            }
        }
        return id;
    }
    double[] Col (int index){
        double[] col = new double[this.getRow()];
        for (int i = 0; i <col.length ; i++) {
            col[i] = this.matrix[index][i];
        }
        return col;
    }
    double[] Row (int index){
        double[] row = new double[this.getCol()];
        for (int i = 0; i <row.length ; i++) {
            row[i] = this.getEntry(i,index);
        }
        return row;
    }
    public Matrix times (Matrix m){
        Matrix prodM = new Matrix(this.col,m.row);
        for (int i = 0; i < this.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                double[]mCol = m.Col(j);
                prodM.setEntry(i,j,dotProduct(this.Col(i),mCol));
            }
        }
        return prodM;
    }
    public Matrix invert (){
        /*
        Matrix m = this;
        Matrix id = idMatrix(m.col);
        if (m.ComplexDeterminant()!= 0) {
            for (int i = 0; i < m.col; i++) {
                for (int j = 0; j < m.col; j++) {
                    id = id.scalarTimesRow((1 / m.getEntry(j, j)), j);
                    m = m.scalarTimesRow((1 / m.getEntry(j, j)), j);
                }
                for (int j = i + 1; j < m.col; j++) {
                    if (m.getEntry(j, i) != 0.0) {
                        id = id.linearComboRows(-m.getEntry(j, i), i, j);
                        m = m.linearComboRows(-m.getEntry(j, i), i, j);
                    }
                }
            }
        }

        for (int i = m.getCol()-1; i > 0 ; i--) {
            for (int j = 0; j <i ; j++) {
                if (m.getEntry(j,i) != 0){
                    id = id.linearComboRows(-m.getEntry(j,i), i,j );
                    m = m.linearComboRows(-m.getEntry(j,i), i,j );
                }
            }
            for (int j = 1; j <m.getCol() ; j++) {
                id = id.scalarTimesRow((1/m.getEntry(j,j)),j);
                m = m.scalarTimesRow((1/m.getEntry(j,j)),j);
            }
        }
         */
        Matrix m = matrixDupe(this);
        Matrix id = idMatrix(m.col);
        ArrayList<int[]> pivots = new ArrayList<>();
        int offset = 0;
        if (m.ComplexDeterminant() != 0) {
            for (int i = 0; i < m.getRow(); i++) {
                if (offset + i < m.getCol()) {
                    if (m.getEntry(i + offset, i) != 0) {
                        int[] chords = new int[2];
                        chords[0] = i + offset;
                        chords[1] = i;
                        pivots.add(chords);
                        id = id.idclearDown(m, i + offset, i);
                        m = m.clearDown(i + offset, i);
                    } else if (oopsAllZeroes(m.Col(i + offset))) {
                        offset++;
                        i--;
                    } else {
                        id = id.switchRows(CrunchZeroes(m.Col(i + offset), i), i);
                        m = m.switchRows(CrunchZeroes(m.Col(i + offset), i), i);
                        i--;
                    }
                }
            }
            System.out.println(offset);
            for (int i = 0; i < pivots.size(); i++) {
                id = id.idclearUp(m, pivots.get(pivots.size() - i - 1)[0], pivots.get(pivots.size() - i - 1)[1]);
                m = m.clearUp(pivots.get(pivots.size() - i - 1)[0], pivots.get(pivots.size() - i - 1)[1]);
            }
        }
        return id;
    }
    public double SimpleDeterminant(){
        return this.getEntry(0,0)*this.getEntry(1,1) - this.getEntry(1,0)*this.getEntry(0,1);
    }
    public java.lang.String toString(){
        String mString = "";
        for (int i = 0; i < row; i++) {
            mString = mString + "[";
            for (int j = 0; j < col; j++) {
                mString = mString + " " + (this.getEntry(j,i)) + " ";
            }
            mString = mString + "]\n";
        }
        return mString;
    }
    public double ComplexDeterminant(){
        ArrayList<Matrix> SmallerMatrix = new ArrayList<Matrix>();
        double det = 0;
        if (this.col ==2){
            det = this.SimpleDeterminant();
            return det;
        }
        for (int i = 0; i <this.getCol() ; i++) {
            if(i%2 == 0){
                det += this.getEntry(i,0)*this.matrixBreaker(i,0).ComplexDeterminant();
            }
            else {
                det -= this.getEntry(i,0)*this.matrixBreaker(i,0).ComplexDeterminant();
            }
        }
        return det;
    }
    public Matrix matrixBreaker(int noCol, int noRow){
        Matrix smallboy = new Matrix(this.getCol()-1, this.getRow()-1);
        int altI =0;
        int altJ =0;
        for (int i = 0; i <this.getCol() ; i++) {
            if (i != noCol){
                for (int j = 0; j <this.getRow() ; j++) {
                    if (j != noRow){
                        smallboy.setEntry(altI,altJ, this.getEntry(i,j));
                        altJ++;
                    }
                }
                altJ = 0;
                altI++;
            }
        }
        return smallboy;
    }
    public Matrix MatrixCleaner(){
        Matrix m = matrixDupe(this);
        for (int i = 0; i <m.getCol() ; i++) {
            for (int j = 0; j <m.getRow() ; j++) {
                m.setEntry(i,j, round(m.getEntry(i,j)));
            }
        }
        return m;
    }
    public double round(double num){
        if (num> -9E-12 && num < 9E-12){
            num = 0;
        }
        if (num-1> -9E-12 && num-1 < 9E-12){
            num = 1;
        }
        return num;
    }
}
