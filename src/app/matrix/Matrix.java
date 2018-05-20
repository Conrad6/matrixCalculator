package app.matrix;

import app.matrix.util.MatrixException;

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
        private int rows;
        private int columns;

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getColumns() {
            return columns;
        }

        public void setColumns(int columns) {
            this.columns = columns;
        }

        public Dimension(int rows, int columns) {
            this.setRows(rows);
            this.setColumns(columns);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Dimension){
                return (this.getColumns() == ((Dimension) obj).getColumns()) && (this.getRows() == ((Dimension) obj).getRows());
            }
            return false;
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
        this(dimension.getRows(), dimension.getColumns(), null);
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

        for (int i = 0; i < this.getDimension().getRows(); i++) {
            for (int j = 0; j < this.getDimension().getColumns(); j++) {
                if (j == 0) builder.append("\t");
                builder.append(this.entries[i][j]);
                if (j != this.getDimension().getColumns() - 1)
                    builder.append(", ");
            }
            if (i != this.getDimension().getRows()) builder.append("\n");
        }

        builder.append("}");

        return builder.toString();
    }

    public boolean isSquare() {
        return this.getDimension().getColumns() == this.getDimension().getRows();
    }
}


