package app.matrix;

import java.util.Arrays;

public class MatrixOperations {

    private static final String MATRIX_MULTIPLICATION_ERROR_MSG = "The matrices cannot be multiplied";

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws MatrixException {

        if (matrix1.getDimension().getVertical() != matrix2.getDimension().getHorizontal())
            throw new MatrixException(MATRIX_MULTIPLICATION_ERROR_MSG);

        Matrix retVal = new Matrix(matrix1.getDimension().getHorizontal(), matrix2.getDimension().getVertical(), matrix1.getName() + " x " + matrix2.getName());


        for (int i = 0; i < matrix1.getDimension().getHorizontal(); i++) {
            for (int j = 0; j < matrix2.getDimension().getVertical(); j++) {
                retVal.getEntries()[i][j] = sum(arrayProduct(matrix1.getEntries()[i], getColumnMatrix(matrix2, j)));
            }
        }

        return retVal;
    }

    private static double[] arrayProduct(double[] arr1, double[] arr2) throws MatrixException {
        if (arr1.length != arr2.length) throw new MatrixException("Array lengths must be the same");
        double[] retVal = new double[arr1.length];
        for(int i = 0; i < arr1.length; i++){
            retVal[i] = arr1[i] * arr2[i];
        }

        return retVal;
    }

    private static double[] getColumnMatrix(Matrix matrix, int col) {
        double[] ret = new double[matrix.getDimension().getHorizontal()];
        for (int i = 0; i < matrix.getDimension().getHorizontal(); i++) {
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

}
