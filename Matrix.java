package Matrix.Lesson09;

/**
 * To  create  type  Matrix,  which  incapsulates  two-dimensional  array-matrix block of real (double) type.
 * Matrix is the cover for two-dimensional array of real values, storing matrix values with operations of matrix addition, deduction and multiplication.
 * <p>
 * Real type values (double) can be in matrix, specifying during creation, the number  of  array  rows  and  columns,  which  will  store  these  values.  After
 * creation, the number of rows and columns are not changed. Values to matrix elements can be set while creating matrix, and later with the help of indexer.
 * Matrix  can  provide  information  regarding  the  number  of  array  rows  and columns, receive array elements in form of two-dimensional standard array,
 * add, deduct and multiply matrixes compatible by size. If a user is trying to perform operations with matrix of incompatible sizes – user type exceptions
 * MatrixException are thrown from operations. Other matrix methods also throw  exceptions,  if  a  user  applies  them  incorrectly  (conveys  incorrect
 * parameters into constructor, in indexer – non-existing index and so on).
 * <p>
 * Implementation of the following functionality is required in Matrix class:
 * •  Creating  of  empty  matrix  with  predetermined  number  of  rows  and columns (all values in matrix equal 0).
 * •  Creating of matrix based on existing two-dimensional array.
 * •  Receiving of number of matrix rows and columns.
 * •  Receiving of standard two-dimensional array out of matrix.
 * •  Access to recording and reading of elements via predetermined correct index (indexer).
 * •  Method of matrixes addition.
 * •  Method of matrixes deduction.
 * •  Method of matrixes multiplication.
 * •  Raise exceptions specified in Javadoc-comments to class methods.
 */
public class Matrix {
    private int row;
    private int column;
    private double[][] matrix;

    private static final String ZERO_SIZE_DIMENSION = "Quantity of rows or columns cannot equal zero";
    private static final String MATRIX_MULTI_ERROR_MSG = "Multiplication is impossible because of incompatible matrices sizes";
    private static final String INCOMPATIBLE_SIZE_ERROR_MSG = "Lengths of matrices are not equals";
    private static final String INVALID_ELEMENT_INDEX = "The element with specified index doesn't exist";
    private static final String NULL_MATRIX_ERR = "The matrix values cannot be empty";

    /**
     * Constructor for creation  of  empty  matrix  with  predetermined  number  of  rows  and columns (all values in matrix equal 0).
     *
     * @param row    quantity of row
     * @param column quantity of column
     * @throws MatrixException if quantity of rows or columns which transferred as method parameters is equal 0, the exception about zero size dimensions will be thrown
     */
    public Matrix(int row, int column) throws MatrixException {
        if (row == 0 || column == 0) throw new MatrixException(ZERO_SIZE_DIMENSION);
        this.row = row;
        this.column = column;
        this.matrix = new double[this.row][this.column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                setMatrixElement(i, j, 0);
            }
        }
    }

    /**
     * Creating of matrix based on existing two-dimensional array.
     *
     * @param matrix two-dimensional array based on which matrix should be created
     * @throws MatrixException if empty (null) two-dimensional array is specified as method parameter, the exception about empty matrix will be thrown
     */
    public Matrix(double[][] matrix) throws MatrixException {
        if (matrix == null) throw new MatrixException(NULL_MATRIX_ERR);
        this.row = matrix.length;
        this.column = matrix[0].length;
        this.matrix = matrix;
    }

    /**
     * Reading of elements via predetermined correct index (indexer).
     *
     * @param row    quantity of row
     * @param column quantity of column
     * @return matrix element with specified indexes
     * @throws MatrixException if specified row or column is out of matrix size, the exception about invalid element index will be thrown
     */
    public double getMatrixElement(int row, int column) throws MatrixException {
        if (row >= getMatrixRowQuantity() || column >= getMatrixColumnQuantity())
            throw new MatrixException(INVALID_ELEMENT_INDEX);
        return this.matrix[row][column];
    }

    /**
     * Recording of elements via predetermined correct index (indexer).
     *
     * @param row    quantity of row
     * @param column quantity of column
     * @param value  value of matrix's element which need to be set
     * @throws MatrixException if specified row or column is out of matrix size, the exception about invalid element index will be thrown
     */
    public void setMatrixElement(int row, int column, double value) throws MatrixException {
        if (row >= getMatrixRowQuantity() || column >= getMatrixColumnQuantity())
            throw new MatrixException(INVALID_ELEMENT_INDEX);
        this.matrix[row][column] = value;
    }

    /**
     * Receiving of number of matrix rows
     *
     * @return Quantity of matrix rows
     */
    public int getMatrixRowQuantity() {
        return this.matrix.length;
    }

    /**
     * Receiving of number of matrix columns
     *
     * @return Quantity of matrix columns
     */
    public int getMatrixColumnQuantity() {
        return this.matrix[0].length;
    }

    /**
     * Receiving of standard two-dimensional array out of matrix.
     *
     * @return matrix
     * @throws MatrixException if matrix is null, the exception about null matrix will be thrown
     */
    public double[][] getMatrix() throws MatrixException {
        if (matrix == null) throw new MatrixException(NULL_MATRIX_ERR);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    /**
     * Method for addition of two matrices. The third matrix is returned as the result
     *
     * @param firstMatrix  first matrix
     * @param secondMatrix second matrix
     * @return resultMatrix is as a result of addition
     * @throws MatrixException if condition for matrices addition is not respected, the exception about differences in matrices sizes will be thrown
     */
    public static Matrix addMatrix(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        if (firstMatrix.getMatrixRowQuantity() != secondMatrix.getMatrixRowQuantity() ||
                firstMatrix.getMatrixColumnQuantity() != secondMatrix.getMatrixColumnQuantity()) {
            throw new MatrixException(INCOMPATIBLE_SIZE_ERROR_MSG);
        } else {
            Matrix resultMatrix = new Matrix(firstMatrix.getMatrixRowQuantity(), secondMatrix.getMatrixColumnQuantity());
            for (int i = 0; i < resultMatrix.getMatrixColumnQuantity(); i++) {
                for (int j = 0; j < resultMatrix.getMatrixRowQuantity(); j++) {
                    resultMatrix.setMatrixElement(i, j, firstMatrix.getMatrixElement(i, j) + secondMatrix.getMatrixElement(i, j));
                }
            }
            return resultMatrix;
        }
    }

    /**
     * Method for subtraction of two matrices. The third matrix is returned as the result
     *
     * @param firstMatrix  first matrix
     * @param secondMatrix second matrix
     * @return resultMatrix is as a result of subtraction
     * @throws MatrixException if condition for matrices subtraction is not respected, the exception about differences in matrices sizes will be thrown
     */
    public static Matrix subtractMatrix(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        if (firstMatrix.getMatrixRowQuantity() != secondMatrix.getMatrixRowQuantity() ||
                firstMatrix.getMatrixColumnQuantity() != secondMatrix.getMatrixColumnQuantity())
            throw new MatrixException(INCOMPATIBLE_SIZE_ERROR_MSG);
        else {
            Matrix resultMatrix = new Matrix(firstMatrix.getMatrixRowQuantity(), secondMatrix.getMatrixColumnQuantity());
            for (int i = 0; i < resultMatrix.getMatrixColumnQuantity(); i++) {
                for (int j = 0; j < resultMatrix.getMatrixRowQuantity(); j++) {
                    resultMatrix.setMatrixElement(i, j, firstMatrix.getMatrixElement(i, j) - secondMatrix.getMatrixElement(i, j));
                }
            }
            return resultMatrix;
        }
    }

    /**
     * Method for multiplication of two matrices. The third matrix is returned as the result
     *
     * @param firstMatrix  first matrix
     * @param secondMatrix second matrix
     * @return resultMatrix is as a result of multiplication
     * @throws MatrixException if condition for matrices multiplication is not respected, the exception about incompatible matrices sizes will be thrown
     */
    public static Matrix multiplyMatrix(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        if (firstMatrix.getMatrixColumnQuantity() != secondMatrix.getMatrixRowQuantity())
            throw new MatrixException(MATRIX_MULTI_ERROR_MSG);

        else {
            Matrix resultMatrix = new Matrix(firstMatrix.getMatrixRowQuantity(), secondMatrix.getMatrixColumnQuantity());
            double[][] tempMatrix = new double[firstMatrix.getMatrixRowQuantity()][secondMatrix.getMatrixColumnQuantity()];
            for (int i = 0; i < firstMatrix.getMatrixRowQuantity(); i++) {
                for (int j = 0; j < secondMatrix.getMatrixColumnQuantity(); j++) {
                    for (int k = 0; k < secondMatrix.getMatrixRowQuantity(); k++) {
                        tempMatrix[i][j] += firstMatrix.getMatrixElement(i, k) * secondMatrix.getMatrixElement(k, j);
                    }
                }
            }
            return resultMatrix = new Matrix(tempMatrix);
        }
    }
}







