package app.matrix.util;

import app.matrix.Matrix;

public class MatrixOperations {

    private static final String MATRIX_MULTIPLICATION_ERROR_MSG = "The matrices cannot be multiplied";

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws MatrixException {

        if (matrix1.getDimension().getColumns() != matrix2.getDimension().getRows())
            throw new MatrixException(MATRIX_MULTIPLICATION_ERROR_MSG);

        Matrix retVal = new Matrix(matrix1.getDimension().getRows(), matrix2.getDimension().getColumns(), matrix1.getName() + " x " + matrix2.getName());


        for (int i = 0; i < matrix1.getDimension().getRows(); i++) {
            for (int j = 0; j < matrix2.getDimension().getColumns(); j++) {
                retVal.getEntries()[i][j] = sum(arrayProduct(matrix1.getEntries()[i], getColumnMatrix(matrix2, j)));
            }
        }

        return retVal;
    }

    private static double[] arrayProduct(double[] arr1, double[] arr2) throws MatrixException {
        if (arr1.length != arr2.length) throw new MatrixException("Array lengths must be the same");
        double[] retVal = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            retVal[i] = arr1[i] * arr2[i];
        }

        return retVal;
    }

    private static double[] getColumnMatrix(Matrix matrix, int col) {
        double[] ret = new double[matrix.getDimension().getRows()];
        for (int i = 0; i < matrix.getDimension().getRows(); i++) {
            ret[i] = matrix.getEntries()[i][col];
        }
        return ret;
    }

    private static double sum(double... values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    public static void multiply(Matrix matrix, double scalar) {
        for (int i = 0; i < matrix.getDimension().getRows(); i++) {
            for (int j = 0; j < matrix.getDimension().getColumns(); j++) {
                matrix.getEntries()[i][j] = matrix.getEntries()[i][j] * scalar;
            }
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) throws MatrixException {

        if (!matrix1.getDimension().equals(matrix2.getDimension()))
            throw new MatrixException("Unequal dimensions for matrix addition");

        Matrix resultMatrix = new Matrix(matrix1.getDimension());

        for (int i = 0; i < matrix1.getDimension().getRows(); i++) {
            for (int j = 0; j < matrix2.getDimension().getColumns(); j++) {
                resultMatrix.getEntries()[i][j] = matrix1.getEntries()[i][j] + matrix2.getEntries()[i][j];
            }
        }

        return resultMatrix;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) throws MatrixException {
        if (!matrix1.getDimension().equals(matrix2.getDimension()))
            throw new MatrixException("Unequal dimensions for matrix addition");

        Matrix resultMatrix = new Matrix(matrix1.getDimension());

        for (int i = 0; i < matrix1.getDimension().getRows(); i++) {
            for (int j = 0; j < matrix2.getDimension().getColumns(); j++) {
                resultMatrix.getEntries()[i][j] = matrix1.getEntries()[i][j] - matrix2.getEntries()[i][j];
            }
        }
        return resultMatrix;
    }

}
