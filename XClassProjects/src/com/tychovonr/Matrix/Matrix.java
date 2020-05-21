package com.tychovonr.Matrix;

import java.util.ArrayList;

public class Matrix {
    double matrix[][];
    int col;
    int row;

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

    public double getEntry(int x, int y) {
        return matrix[x][y];
    }

    public void setEntry(int x, int y, double val) {
        matrix[x][y] = val;
    }

    /**
     * Returns a new matrix equal to the sum of two other matrices
     *
     * @param m
     * @return
     */
    public Matrix add(Matrix m) {
        Matrix addM = new Matrix(this.col, this.row);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.col; j++) {
                addM.setEntry(i, j, this.getEntry(i, j) + m.getEntry(i, j));
            }
        }
        return addM;
    }

    /**
     * Creates an identity matrix of a certain side length
     *
     * @param sideLength
     * @return
     */
    public Matrix idMatrix(int sideLength) {
        Matrix idM = new Matrix(sideLength, sideLength);
        for (int i = 0; i < sideLength; i++) {
            idM.matrix[i][i] = 1;
        }
        return idM;
    }

    /**
     * Makes a copy of a matrix for use in functions, I found that the matrix would start changing itself otherwise, so this matrix gets around that
     *
     * @param m
     * @return
     */
    public Matrix matrixDupe(Matrix m) {
        Matrix dupM = new Matrix(this.col, this.row);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                dupM.matrix[i][j] = m.matrix[i][j];
            }
        }
        return dupM;
    }

    /**
     * Switches two rows
     *
     * @param firstRow
     * @param secondRow
     * @return
     */
    public Matrix switchRows(int firstRow, int secondRow) {
        Matrix switchM = matrixDupe(this);
        for (int i = 0; i < switchM.getCol(); i++) {
            switchM.setEntry(i, firstRow, this.getEntry(i, secondRow));
            switchM.setEntry(i, secondRow, this.getEntry(i, firstRow));
        }
        return switchM;
    }

    /**
     * Does what it says
     *
     * @param scalar
     * @param firstRow
     * @param secondRow
     * @return
     */
    public Matrix linearComboRows(double scalar, int firstRow, int secondRow) {
        Matrix comboM = this;
        comboM = comboM.scalarTimesRow(scalar, firstRow);
        for (int i = 0; i < comboM.getCol(); i++) {
            comboM.matrix[i][secondRow] += comboM.matrix[i][firstRow];
        }
        comboM = comboM.scalarTimesRow(1 / scalar, firstRow);

        return comboM;
    }

    /**
     * Does what it says
     *
     * @param scalar
     * @param rowNumber
     * @return
     */
    public Matrix scalarTimesRow(double scalar, int rowNumber) {
        Matrix scalM = this;
        for (int i = 0; i < scalM.getCol(); i++) {
            scalM.setEntry(i, rowNumber, scalM.getEntry(i, rowNumber) * scalar);
        }
        return scalM;
    }

    /**
     * Does what it says
     *
     * @param m
     * @return
     */
    public Matrix times(Matrix m) {
        Matrix dupe = matrixDupe(this);
        Matrix prodM = new Matrix(m.getCol(), dupe.getRow());
        for (int i = 0; i < dupe.getRow(); i++) {
            for (int j = 0; j < m.getCol(); j++) {
                prodM.setEntry(j,i, dotProduct(dupe.Row(i), m.Col(j)));
            }
        }
        return prodM;
    }

    /**
     * Gets the dot product of a row and a column
     *
     * @param col
     * @param row
     * @return
     */
    public Double dotProduct(double[] col, double[] row) {
        double dProd = 0;
        if (col.length == row.length) {
            for (int i = 0; i < col.length; i++) {
                dProd += col[i] * row[i];
            }
        }
        return dProd;
    }

    /**
     * Checks if a row is all zeroes or not
     *
     * @param row
     * @return
     */
    public boolean oopsAllZeroes(double[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0.0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds a none zero element of a row
     *
     * @param row
     * @param startInd
     * @return
     */
    public int crunchZeroes(double[] row, int startInd) {
        for (int i = startInd; i < row.length; i++) {
            if (row[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Sorts all of the rows with only zero in them to the bottom
     *
     * @return
     */
    public Matrix sortZeroes() {
        Matrix m = this;
        int swapcounter = 0;
        for (int i = 0; i < m.getRow(); i++) {
            if (oopsAllZeroes(m.Row(m.getRow() - i - 1)) == true) {
                m = m.switchRows(m.getRow() - i - 1, m.getRow() - swapcounter - 1);
                swapcounter++;
            }
        }
        return m;
    }

    /**
     * Row Reduces a matrix by clearing down column by column and keeping track of the pivots, and then once that is done, going to each pivot and clearing up.
     *
     * @return
     */
    public Matrix rowReduce() {
        Matrix m = matrixDupe(this);
        m = m.sortZeroes();
        ArrayList<int[]> pivots = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i < m.getRow(); i++) {
            if (offset + i < m.getCol()) {
                if (m.getEntry(i + offset, i) != 0) {
                    int[] chords = new int[2];
                    chords[0] = i + offset;
                    chords[1] = i;
                    pivots.add(chords);
                    m = m.clearDown(i + offset, i);
                } else if (oopsAllZeroes(m.Col(i + offset))) {
                    offset++;
                    i--;
                } else {
                    m = m.switchRows(crunchZeroes(m.Col(i + offset), i), i);
                    i--;
                }
            }
        }
        System.out.println(offset);
        for (int i = 0; i < pivots.size(); i++) {
            m = m.clearUp(pivots.get(pivots.size() - i - 1)[0], pivots.get(pivots.size() - i - 1)[1]);
        }
        return m;
    }

    /**
     * Makes all of the elements below a certain point in a matrix zero
     *
     * @param x
     * @param y
     * @return
     */
    public Matrix clearDown(int x, int y) {
        Matrix m = matrixDupe(this);
        for (int i = 0; i < m.getRow() - y; i++) {
            m = m.scalarTimesRow(1 / m.getEntry(x, y), y);
            if (m.getEntry(x, y + i) != 0) {
                m = m.linearComboRows(-m.getEntry(x, y + i), y, y + i);
            }
        }
        return m;
    }

    /**
     * Makes all of the elements above a certain point in a matrix zero
     *
     * @param x
     * @param y
     * @return
     */
    public Matrix clearUp(int x, int y) {
        Matrix m = matrixDupe(this);
        for (int i = 0; i < y; i++) {
            m = m.scalarTimesRow(1 / m.getEntry(x, y), y);
            if (m.getEntry(x, i) != 0) {
                m = m.linearComboRows(-m.getEntry(x, i), y, i);
            }
        }
        return m;
    }

    /**
     * Does the same thing as clear up, I just am lazy so I copied and pasted to makes sure that it did the same thing to a identity matrix at the same time
     *
     * @param m
     * @param x
     * @param y
     * @return
     */
    public Matrix idclearDown(Matrix m, int x, int y) {
        Matrix id = matrixDupe(this);
        for (int i = 0; i < m.getRow() - y; i++) {
            id = id.scalarTimesRow(1 / m.getEntry(x, y), y);
            m = m.scalarTimesRow(1 / m.getEntry(x, y), y);
            if (m.getEntry(x, y + i) != 0) {
                id = id.linearComboRows(-m.getEntry(x, y + i), y, y + i);
                m = m.linearComboRows(-m.getEntry(x, y + i), y, y + i);
            }
        }
        return id;
    }

    public Matrix idclearUp(Matrix m, int x, int y) {
        Matrix id = matrixDupe(this);
        for (int i = 0; i < y; i++) {
            id = id.scalarTimesRow(1 / m.getEntry(x, y), y);
            m = m.scalarTimesRow(1 / m.getEntry(x, y), y);
            if (m.getEntry(x, i) != 0) {
                id = id.linearComboRows(-m.getEntry(x, i), y, i);
                m = m.linearComboRows(-m.getEntry(x, i), y, i);
            }
        }
        return id;
    }

    /**
     * Returns a specific column
     *
     * @param index
     * @return
     */
    double[] Col(int index) {
        double[] col = new double[this.getRow()];
        for (int i = 0; i < col.length; i++) {
            col[i] = this.matrix[index][i];
        }
        return col;
    }

    /**
     * Returns a specific row
     *
     * @param index
     * @return
     */
    double[] Row(int index) {
        double[] row = new double[this.getCol()];
        for (int i = 0; i < row.length; i++) {
            row[i] = this.getEntry(i, index);
        }
        return row;
    }

    /**
     * Does the same thing as row reduce, just only works for square matrices, and checks if it is inversible first, then does the same steps, just with a identity matrix as well
     *
     * @return
     */
    public Matrix invert() {
        Matrix m = matrixDupe(this);
        Matrix id = idMatrix(m.col);
        ArrayList<int[]> pivots = new ArrayList<>();
        int offset = 0;
        if (m.complexDeterminant() != 0) {
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
                        id = id.switchRows(crunchZeroes(m.Col(i + offset), i), i);
                        m = m.switchRows(crunchZeroes(m.Col(i + offset), i), i);
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

    /**
     * Returns the determinant of a 2x2 matrix
     *
     * @return
     */
    public double simpleDeterminant() {
        return this.getEntry(0, 0) * this.getEntry(1, 1) - this.getEntry(1, 0) * this.getEntry(0, 1);
    }

    public java.lang.String toString() {
        String mString = "";
        for (int i = 0; i < row; i++) {
            mString = mString + "[";
            for (int j = 0; j < col; j++) {
                mString = mString + " " + (this.getEntry(j, i)) + " ";
            }
            mString = mString + "]\n";
        }
        return mString;
    }

    /**
     * Breaks down any square matrix into smaller and smaller matrices and adds the determinants of those matrices together to find the determinant
     *
     * @return
     */
    public double complexDeterminant() {
        double det = 0;
        if (this.col == 2) {
            det = this.simpleDeterminant();
            return det;
        }
        for (int i = 0; i < this.getCol(); i++) {
            if (i % 2 == 0) {
                det += this.getEntry(i, 0) * this.matrixBreaker(i, 0).complexDeterminant();
            } else {
                det -= this.getEntry(i, 0) * this.matrixBreaker(i, 0).complexDeterminant();
            }
        }
        return det;
    }

    /**
     * Returns a matrix sans one specified column and row
     *
     * @param noCol
     * @param noRow
     * @return
     */
    public Matrix matrixBreaker(int noCol, int noRow) {
        Matrix smallboy = new Matrix(this.getCol() - 1, this.getRow() - 1);
        int altI = 0;
        int altJ = 0;
        for (int i = 0; i < this.getCol(); i++) {
            if (i != noCol) {
                for (int j = 0; j < this.getRow(); j++) {
                    if (j != noRow) {
                        smallboy.setEntry(altI, altJ, this.getEntry(i, j));
                        altJ++;
                    }
                }
                altJ = 0;
                altI++;
            }
        }
        return smallboy;
    }

    /**
     * Just makes the matrix look nicer by rounding off everything that is near zero and one
     *
     * @return
     */
    public Matrix matrixClean() {
        Matrix m = matrixDupe(this);
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                m.setEntry(i, j, round(m.getEntry(i, j)));
            }
        }
        return m;
    } 

    public double round(double num) {
        if (num > -9E-12 && num < 9E-12) {
            num = 0;
        }
        if (num - 1 > -9E-12 && num - 1 < 9E-12) {
            num = 1;
        }
        return num;
    }

    public Matrix vdmMaker(double[] poly,double num) {
        Matrix system = idMatrix(poly.length);
        for (int i = 0; i <poly.length-2 ; i++) {
            system.setEntry(i+2,i+1, -2*num);
            system.setEntry(i+2, i, num*num);
        }
        System.out.println(system.toString());
        return system;
    }
    public Matrix coeffecientMaker(double[] poly){
        Matrix m = new Matrix(1,poly.length);
        for (int i = 0; i < poly.length; i++) {
            m.setEntry(0,i,poly[i]);
        }
        return m;
    }
    public double derive(double[] poly, double num){
        Matrix m = vdmMaker(poly,num);
        Matrix coef = coeffecientMaker(poly);
        m = m.invert();
        System.out.println(m);
        System.out.println(coef);
        return (dotProduct(m.Row(1),poly));
    }
}
