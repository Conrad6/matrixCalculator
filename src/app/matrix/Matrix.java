package app.matrix;

import java.util.Arrays;
import java.util.List;

public class Matrix {

    private double[][] entries;

    public void setEntries(double[][] entries) {
        this.entries = entries;
    }

    private String name;

    public double[][] getEntries() {
        return entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Dimension {
        private int horizontal;
        private int vertical;

        public int getHorizontal() {
            return horizontal;
        }

        public void setHorizontal(int horizontal) {
            this.horizontal = horizontal;
        }

        public int getVertical() {
            return vertical;
        }

        public void setVertical(int vertical) {
            this.vertical = vertical;
        }

        public Dimension(int horizontal, int vertical) {
            this.setHorizontal(horizontal);
            this.setVertical(vertical);
        }
    }

    private Dimension dimension;

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Matrix(int dimension, String name) throws MatrixException {
        if (dimension == 0) throw new MatrixException(ZERO_UNIT_DIMENSION);
        else this.setEntries(new double[Math.abs(dimension)][Math.abs(dimension)]);
        this.setDimension(new Dimension(dimension, dimension));
        this.setName(name);
    }

    public Matrix(int dimensionH, int dimensionV, String name) throws MatrixException {
        if (dimensionH == 0 || dimensionV == 0) throw new MatrixException(ZERO_UNIT_DIMENSION);
        else this.setEntries(new double[Math.abs(dimensionH)][Math.abs(dimensionV)]);
        this.setDimension(new Dimension(dimensionH, dimensionV));
        this.setName(name);

    }

    private static final String OVERFLOW_ITEMS_MSG = "The values are too many for the matrix's specified dimensions";
    private static final String ZERO_UNIT_DIMENSION = "Zero cannot be a value for a dimension";

    public Matrix(int dimensionH, int dimensionV, String name, double... values) throws MatrixException {
        if (dimensionH == 0 || dimensionV == 0) throw new MatrixException(ZERO_UNIT_DIMENSION);
        else if (values.length > dimensionH * dimensionV) throw new MatrixException(Matrix.OVERFLOW_ITEMS_MSG);
        else this.setEntries(new double[Math.abs(dimensionH)][Math.abs(dimensionV)]);
        this.setDimension(new Dimension(dimensionH, dimensionV));
        this.setName(name);

        int iterator = 0;
        int j;
        for (int i = 0; i < dimensionH; i++) {
            j = 0;
            while (j < dimensionV) {
                this.entries[i][j] = values[iterator];
                j++;
                iterator++;
            }
        }
    }

    public Matrix(Dimension dimension) throws MatrixException {
        this(dimension.getHorizontal(), dimension.getVertical(), null);
    }

    public static Matrix identityMatrix(int dim) throws MatrixException {
        if (dim == 0) throw new MatrixException(ZERO_UNIT_DIMENSION);

        double[] i = new double[dim * dim];
        int constant = dim + 1;
        for (int j = 0; j < i.length; j = j + constant) {
            i[j] = 1.0;
        }

        return new Matrix(dim, dim, null, i);
    }

    public String toString() {

        StringBuilder builder = new StringBuilder("Matrix \"" + (this.getName() == null ? "Null Matrix" : this.getName()) + "\": {\n");

        for (int i = 0; i < this.getDimension().getHorizontal(); i++) {
            for (int j = 0; j < this.getDimension().getVertical(); j++) {
                if (j == 0) builder.append("\t");
                builder.append(this.entries[i][j]);
                if (j != this.getDimension().getVertical() - 1)
                    builder.append(", ");
            }
            if (i != this.getDimension().getHorizontal()) builder.append("\n");
        }

        builder.append("}");

        return builder.toString();
    }

    public static void main(String[] args) {
        try {
            Matrix m1 = new Matrix(3, 3, "dj", 1, 2, 4, 0, 1, 1, 1, 3, 5);
            System.out.println(m1);
            Matrix m2 = new Matrix(3, 2, "dj", 2, -1, 3, 1, 9, -2);
            System.out.println(m2);
            System.out.println(MatrixOperations.multiply(m1, m2));
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }

}

class MatrixException extends Exception {
    public MatrixException(String message) {
        super(message);
    }
}
